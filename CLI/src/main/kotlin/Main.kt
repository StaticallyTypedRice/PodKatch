package podkatch.cli

import java.net.URL
import java.io.File
import java.io.FileNotFoundException
import java.net.MalformedURLException
import org.w3c.dom.Document
import com.github.kittinunf.fuel.core.Request

import podcastengine.rss.*

fun main(args: Array<String>) {

    println()
    println(" ############### PodKatch CLI ###############")
    println(" #                                          #")
    println(" # Created by Richie Zhang                  #")
    println(" # Version 0.1                              #")
    println(" #                                          #")
    println(" # Downloads all podcasts in an RSS file.   #")
    println(" #                                          #")
    println(" ############################################")
    println()

    var location: String
    var outputDir: String
    var remoteRss: Boolean = false
    var remoteRssInput: String?
    var rssURL = URL("https://example.com/")
    var rssFile = File("")

    // Ask if the RSS file is remote or local
    do {
        println("Is the RSS file remote or local?")
        println("1: Remote (default)    2: Local")
        remoteRssInput = readLine()

        if ((remoteRssInput == "1") || (remoteRssInput == "")) {
            remoteRss = true
        } else if (remoteRssInput == "2") {
            remoteRss = false
        } else {
            println()
            println("Invalid input. Please enter '1', '2', or a blank line.")
        }

        // If the input is a blank line, there is no need to print another one.
        if (remoteRssInput != "") {
            println()
        }

    } while ((remoteRssInput != "") && (remoteRssInput != "1") && (remoteRssInput != "2"))

    // Ask for the RSS location
    do {
        print("Podcast RSS: ")
        location = readLine()!!

        // Validate the RSS location
        if (remoteRss) {
            try {
                rssURL = URL(location)
            } catch (e: MalformedURLException) {
                println("Error: URL invalid (${e.message})")
                location = ""
            } catch (e: Exception) {
                println("Error: ${e.message}")
                location = ""
            }
        } else {
            try {
                rssFile = File(location)
            } catch (e: Exception) {
                println("Error: ${e.message}")
                location = ""
            }
        }
    } while (location == "")

    // Ask for the download location
    print("Download to (download): ")
    outputDir = readLine()!!
    if (outputDir == "") {
        outputDir = "download"
    }

    if (remoteRss) {

        val request: Request = getRemoteRss(rssURL)
        println("Downloading RSS file...")

        val (rssRequest, rssResponse, rssRresult) = request.response()
        val (rssBytes, rssError) = rssRresult
        val rssDocument: Document = parseRss(rssBytes)

        download(rssDocument, outputDir)

    } else {
        var fileValid: Boolean

        do {
            try {
                fileValid = true
                val rssFile = parseRss(getLocalRss(location))
                download(rssFile, outputDir)
            } catch (e: FileNotFoundException) {
                fileValid = false
                println("Error: ${e.message}")
                println()

                // Ask for the location again
                location = ""
                do {
                    print("Podcast RSS: ")
                    location = readLine()!!
                } while (location == "")
            }
        } while (!fileValid)
    }

}

/**
 * Download all podcasts listed in an RSS Document object.
 *
 * @param rss The RSS Document object.
 * @param downloadTo The path of the directory to download the file to.
 */
fun download(rss: Document, downloadTo: String) {
    val podcast = createPodcastFromRss(rss)

    for (episode in podcast.episodes) {
        val download = episode.download()
    }
}

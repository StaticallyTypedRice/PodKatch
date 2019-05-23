package podkatch.cli

import java.net.URL
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
    } while (location == "")

    // Ask for the download location
    print("Download to (download): ")
    outputDir = readLine()!!
    if (outputDir == "") {
        outputDir = "download"
    }

    if (remoteRss) {
        var onlineRssValid: Boolean

        do {
            try {
                onlineRssValid = true
                val request: Request = getRemoteRss(URL(location))
                println("Downloading RSS file...")

                val (rssRequest, rssResponse, rssRresult) = request.response()
                val (rssBytes, rssError) = rssRresult
                val rssFile: Document = parseRss(rssBytes)

                download(rssFile, outputDir)
            } catch (e: MalformedURLException) {
                onlineRssValid = false
                println("Error: URL invalid (${e.message})")

                // Ask for the location again
                location = ""
                do {
                    print("Podcast RSS: ")
                    location = readLine()!!
                } while (location == "")
            }
        } while (!onlineRssValid)

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

fun download(RSS: Document, downloadTo: String) {
    val podcast = createPodcastFromRss(RSS)

    for (episode in podcast.episodes) {
        episode.download(downloadTo)
    }
}

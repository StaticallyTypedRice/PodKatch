package podkatch.cli

import java.net.URL
import java.io.FileNotFoundException
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
    var remoteRSS: Boolean = false
    var remoteRSSInput: String?

    // Ask if the RSS file is remote or local
    do {
        println("Is the RSS file remote or local?")
        println("1: Remote (default)    2: Local")
        remoteRSSInput = readLine()

        if ((remoteRSSInput == "1") || (remoteRSSInput == "")) {
            remoteRSS = true
        } else if (remoteRSSInput == "2") {
            remoteRSS = false
        } else {
            println()
            println("Invalid input. Please enter '1', '2', or a blank line.")
        }

        // If the input is a blank line, there is no need to print another one.
        if (remoteRSSInput != "") {
            println()
        }

    } while ((remoteRSSInput != "") && (remoteRSSInput != "1") && (remoteRSSInput != "2"))

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

    if (remoteRSS) {
        val request: Request = getRemoteRSS(URL(location))

        val response = request.response()

        val rssFile: Document = parseRSS(response.toString())

        download(rssFile, outputDir)


    } else {
        var fileValid = false

        do {
            try {
                val rssFile = parseRSS(getLocalRSS(location))
                fileValid = true
            } catch (e: FileNotFoundException) {
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
    val podcast = createPodcastFromRSS(RSS)

    for (episode in podcast.episodes) {
        episode.download(downloadTo)
    }
}

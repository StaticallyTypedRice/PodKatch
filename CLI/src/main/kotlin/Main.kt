package podkatch.cli

import kotlin.coroutines.Continuation
import java.net.URL
import org.w3c.dom.Document

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

    startDownload(remoteRSS, location, outputDir)
}

fun startDownload(remote: Boolean, location: String, directory: String) {
    if (remote) {
        getRemoteRSS(URL(location))

    } else {
        val rssFile: Document = getLocalRSS(location)
    }
}

fun download(RSS: Document) {

}

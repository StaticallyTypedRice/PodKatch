package podkatch.cli

import java.lang.NumberFormatException

fun main(args: Array<String>) {

    var location: String
    var outputDir: String
    var remoteRSS: Boolean
    var remoteRSSInput: String?

    // Ask if the RSS file is remote or local
    do {
        println("Is the RSS file remote or local?\n1: Remote (default)    2: Local\n")
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

    println("TODO: Download podcasts from $location to $outputDir")
}

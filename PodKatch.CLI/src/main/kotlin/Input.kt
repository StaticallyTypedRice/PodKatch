package podkatch.cli.input

import java.io.File
import java.net.MalformedURLException
import java.net.URL

/**
 * Ask if the RSS file is remote or local.
 *
 * @returns A boolean that is true if the RSS is remote and false if the RSS if local.
 */
fun askIfRemoteRss(): Boolean {
    var remoteRssInput: String?
    var remoteRss = false

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

    return remoteRss
}

/**
 * Ask for the RSS URL with validation.
 *
 * @returns The RSS URL object.
 */
fun askForRssUrl(): URL {
    var location: String
    var rssUrl = URL("https://example.com/")
    do {
        print("Podcast RSS: ")
        location = readLine()!!

        // Validate the RSS URL
        try {
            rssUrl = URL(location)
        } catch (e: MalformedURLException) {
            println("Error: URL invalid (${e.message})")
            location = ""
        } catch (e: Exception) {
            println("Error: ${e.message}")
            location = ""
        }

    } while (location == "")

    return rssUrl
}

/**
 * Ask for the RSS file path with validation.
 *
 * @returns The RSS File object.
 */
fun askForRssFile(): File {
    var location: String
    var rssFile = File("")

    do {
        print("Podcast RSS: ")
        location = readLine()!!

        // Validate the RSS file
        try {
            rssFile = File(location)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            location = ""
        }
    } while (location == "")

    return rssFile
}

/**
 * Ask for the download location
 *
 * @returns A string containing the download directory path.
 */
fun askForDownloadLocation(): String {
    var outputDir: String
    print("Download to (download): ")
    outputDir = readLine()!!
    if (outputDir == "") {
        outputDir = "download"
    }
    return outputDir
}

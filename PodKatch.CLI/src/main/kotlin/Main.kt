package podkatch.cli

import java.net.URL
import java.io.File
import java.io.FileNotFoundException
import org.w3c.dom.Document
import com.github.kittinunf.fuel.core.Request

import podkatch.cli.input.*
import podcastengine.rss.*
import podcastengine.objects.PodcastSource

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

    var rssUrl = URL("https://example.com/")
    var rssFile = File("")

    // Ask if the RSS file is remote or local
    val remoteRss = askIfRemoteRss()

    // Ask for the RSS location
    if (remoteRss) {
        rssUrl = askForRssUrl()
    } else {
        rssFile = askForRssFile()
    }

    // Ask for the download location
    val outputDir = askForDownloadLocation()

    if (remoteRss) {

        val request: Request = getRemoteRss(rssUrl)

        val source = PodcastSource("url", rssUrl.toString())

        println("Downloading RSS file...")

        val (rssRequest, rssResponse, rssRresult) = request.response()
        val (rssBytes, rssError) = rssRresult
        val rssDocument: Document = parseRss(rssBytes)

        download(rssDocument, outputDir, source)

    } else {
        var fileValid: Boolean

        do {
            try {
                fileValid = true
                val rssDocument = parseRss(getLocalRss(rssFile))
                download(rssDocument, outputDir)
            } catch (e: FileNotFoundException) {
                fileValid = false
                println("Error: ${e.message}")
                println()

                // Ask for the location again
                rssFile = askForRssFile()
            }
        } while (!fileValid)
    }
}

/**
 * Download all podcasts listed in an RSS Document object.
 *
 * @param rss The RSS Document object.
 * @param downloadTo The path of the directory to download the file to.
 * @param source The podcast source
 */
private fun download(rss: Document, downloadTo: String, source: PodcastSource? = null) {
    val podcast = createPodcastFromRss(rss, source)

    for (episode in podcast.episodes) {
        val download = episode.download()
        println("TODO: Download episode '${episode.title}'")
    }
}

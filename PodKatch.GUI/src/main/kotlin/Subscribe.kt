package podkatch.gui.subscribe

import java.io.File
import java.net.URL
import org.w3c.dom.Document
import com.github.kittinunf.result.Result
import javafx.scene.control.Alert
import podcastengine.objects.PodcastSource
import podcastengine.objects.PodcastSourceType
import tornadofx.*

import podcastengine.rss.getRemoteRss
import podcastengine.rss.parseRss
import podcastengine.subscription.subscribe
import podkatch.gui.error.simpleErrorAlert

import podkatch.gui.views.dialogs.AlertDialog

/**
 * Subscribe to a podcast given the RSS file.
 * Parse the RSS file and call the main subscribe function.
 *
 * @param source The source of the podcast.
 */
fun subscribeFromRss(source: URL) {
    val httpAsync = getRemoteRss(source)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        runLater {
                            runLater { simpleErrorAlert("Could not download file", error.message) }
                        }
                    }
                    is Result.Success -> {
                        try {
                            val rss = parseRss(result.get())
                            subscribe(rss, PodcastSource(PodcastSourceType.FILE, source.toString()))
                        } catch (e: Exception) {
                            runLater {
                                runLater { simpleErrorAlert("Could not parse file", e.message) }
                            }
                        }
                    }
                }
            }

    httpAsync.join()
}
fun subscribeFromRss(source: File) {
    if (source.exists()) {
        try {
            val rss = parseRss(source)
            subscribe(rss, PodcastSource(PodcastSourceType.FILE, source.toString()))
        } catch (e: Exception) {
            runLater { simpleErrorAlert("Could not parse file.", e.message) }

        }
    } else {
        runLater {
            runLater { simpleErrorAlert("The file does not exist.", source.toString()) }
        }
    }
}



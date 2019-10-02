package podkatch.gui.subscribe

import java.io.File
import java.net.URL
import org.w3c.dom.Document
import com.github.kittinunf.result.Result
import javafx.scene.control.Alert
import tornadofx.*

import podcastengine.rss.getRemoteRss
import podcastengine.rss.parseRss


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
                            alert(Alert.AlertType.ERROR, "Could not download file", error.message)
                        }
                    }
                    is Result.Success -> {
                        try {
                            val rss = parseRss(result.get())
                            subscribe(rss)
                        } catch (e: Exception) {
                            runLater {
                                alert(Alert.AlertType.ERROR, "Could not parse file", e.message)
                            }
                        }
                    }
                }
            }

    httpAsync.join()
}
fun subscribeFromRss(source: File) {
    println("TODO")
}

/**
 * Subscribe to a podcast given an RSS Document.
 *
 * @param rss The podcast RSS Document.
 */
fun subscribe(rss: Document) {
    println("TODO")
}
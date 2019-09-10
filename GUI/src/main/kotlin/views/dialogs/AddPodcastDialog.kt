package podkatch.gui.views.dialogs

import podkatch.gui.subscribe.*

import tornadofx.*
import java.net.URL

class AddPodcastDialog : View("Subscribe to a Podcast") {
    override val root = vbox(2) {
        setMaxSize(600.toDouble(), 0.toDouble())
        addClass("dialog")

        label("Enter the url of a podcast RSS file:")
        hbox(5) {
            val rssUrlField = textfield {
                //minWidth(450.toDouble())
            }
            button("OK") {
                tooltip("Subscribe to the podcast at this URL")
                action {
                    println("RSS URL: ${rssUrlField.text}")

                    if (rssUrlField.text.isNotEmpty()) {
                        subscribeFromRss(URL(rssUrlField.text))
                    }
                }
            }
        }
        spacer()

        label("Or import a local RSS file:")
        button("Choose file...") {
            tooltip("Import a local RSS file")
            action {
                val rssPathField = chooseFile("Import an RSS file",
                        filters=arrayOf(), mode=FileChooserMode.Single)
            }
        }
        spacer()

        label("Or:")
        hbox(5) {
            val podcastPlatformSources = arrayOf("iTunes", "gPodder", "fyyd")

            podcastPlatformSources.forEach {
                button("Browse or Search $it") {
                    tooltip("Subscribe to a podcast from $it")
                    action {

                    }
                }
            }
        }
    }

}

package podkatch.gui.views.dialogs

import tornadofx.*

class AddPodcastDialog : View("Add Podcast") {
    override val root = vbox {
        setMaxSize(500.toDouble(), 250.toDouble())

        label("Enter the url of a podcast RSS file:")
        hbox {
            val rssUrlField = textfield {
                //minWidth(450.toDouble())
            }
            button("OK") {
                tooltip("Subscribe to the podcast at this URL")
                action {
                    println("RSS URL: ${rssUrlField.text}")
                }
            }
        }

        label("Or import a local RSS file:")
        button("Choose file...") {
            tooltip("Import a local RSS file")
            action {
                val rssPathField = chooseFile("Import an RSS file",
                        filters=arrayOf(), mode=FileChooserMode.Single)
            }
        }

        label("Or:")
        hbox {
            val podcastPlatformSources = arrayOf("iTunes", "gPodder", "fyyd")

            podcastPlatformSources.forEach {
                button("Browse or Search ${it}") {
                    tooltip("Subscribe to a podcast from ${it}")
                    action {

                    }
                }
            }
        }
    }

}


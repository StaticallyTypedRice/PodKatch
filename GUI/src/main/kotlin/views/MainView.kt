package kodkatch.gui.views

import tornadofx.*

class MainView : View("PodKatch Podcast Client") {
    override val root = borderpane {
        style {
            importStylesheet("/css/third-party/goliath/Goliath-Base.css")
            importStylesheet("/css/third-party/goliath/Goliath-Light.css")
            importStylesheet("/css/PodKatch-Main.css")
            importStylesheet("/css/PodKatch-Light.css")
        }
        val sidebar = left{
            vbox {
                addClass("sidebar")

                val addPodcastBtn = button("Add podcast")
                val updateBtn = button("Update podcasts")
                separator()
                label("Subscribed podcasts")
                separator()
                label("Everything else in the sidebar.")
            }
        }
        val window = right {
            vbox {
                addClass("window")

                label("Everything else will go here.")
                val playerControls = hbox {
                    label("Play/pause controls")
                }
            }
        }

    }
}

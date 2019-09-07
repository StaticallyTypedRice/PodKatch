package podkatch.gui.views

import tornadofx.*

class Sidebar : View() {
    override val root = vbox {
        addClass("sidebar")

        val addPodcastBtn = button("Add podcast") {
            action {
                println("TODO: Create a dialog box for adding podcasts.")
            }
        }
        val updateBtn = button("Update podcasts") {
            action {
                println("TODO: Automatically update all subscribed RSS files and download new podcast episodes.")
            }
        }
        separator()
        label("Subscribed podcasts")
        separator()
        label("Everything else in the sidebar.")
    }
}
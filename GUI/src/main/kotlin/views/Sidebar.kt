package podkatch.gui.views

import tornadofx.*

class Sidebar : View() {
    override val root = vbox {
        addClass("sidebar")

        val addPodcastBtn = button("Add podcast")
        val updateBtn = button("Update podcasts")
        separator()
        label("Subscribed podcasts")
        separator()
        label("Everything else in the sidebar.")
    }
}
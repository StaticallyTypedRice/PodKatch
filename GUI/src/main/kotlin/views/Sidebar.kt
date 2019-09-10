package podkatch.gui.views

import podkatch.gui.views.dialogs.*
import tornadofx.*

class Sidebar : View() {
    override val root = vbox {
        addClass("sidebar")

        val addPodcastBtn = button("Add podcast") {
            action {
                tooltip("Subscribe to a podcast")
                AddPodcastDialog().openModal()
            }
        }
        val editPodcastBtn = button("Edit podcasts") {
            action {
                println("TODO: Create a dialog box for editing podcasts.")
                tooltip("Edit your podcast subscriptions")
            }
        }
        val updateBtn = button("Update podcasts") {
            action {
                println("TODO: Automatically update all subscribed RSS files and download new podcast episodes.")
                tooltip("Update all subscribed podcasts")
            }
        }
        separator()
        label("Subscribed podcasts")
        separator()
        label("Everything else in the sidebar.")
    }
}
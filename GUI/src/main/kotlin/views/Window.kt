package podkatch.gui.views

import tornadofx.*

class Window : View() {
    override val root = vbox {
        addClass("window")

        label("Everything else will go here.")
        button("Placeholder button")
    }
}

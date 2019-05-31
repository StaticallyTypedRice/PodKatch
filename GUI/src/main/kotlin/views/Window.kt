package podkatch.gui.views

import tornadofx.*

class Window : View() {
    override val root = borderpane {
        top {
            vbox {
                addClass("window")

                label("Everything else will go here.")
                button("Placeholder button")
            }
        }
        bottom(PlayerControls::class)
    }
}

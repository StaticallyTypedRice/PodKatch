package podkatch.gui.views

import tornadofx.*

class PlayerControls : View() {
    override val root = hbox {
        addClass("player")
        label("Play/pause controls")
    }
}

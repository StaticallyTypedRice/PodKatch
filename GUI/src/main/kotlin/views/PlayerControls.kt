package podkatch.gui.views

import tornadofx.*
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.WizardStyles.Companion.graphic

class PlayerControls : View() {
    override val root = hbox {
        addClass("player")
        val backwardBtn = button {
            graphic = FontIcon().apply {
                iconLiteral="ti-control-backward"
                iconSize=25
            }

        }
        val playBtn = button {
            graphic = FontIcon().apply {
                iconLiteral="ti-control-play"
                iconSize=25
            }

        }
        val forwardBtn = button {
            graphic = FontIcon().apply {
                iconLiteral="ti-control-forward"
                iconSize=25
            }

        }
    }
}

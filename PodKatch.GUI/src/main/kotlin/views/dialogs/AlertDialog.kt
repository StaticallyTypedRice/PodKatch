package podkatch.gui.views.dialogs

import tornadofx.*

// Customizes the JavaFX Alert dialogs
// Set the owner of alerts to AlertDialog().mainWindow to apply this view
class AlertDialog : View() {
    override val root = vbox{
        style {
            importStylesheet("/css/third-party/goliath/Goliath-Base.css")
            importStylesheet("/css/third-party/goliath/Goliath-Light.css")
            importStylesheet("/css/PodKatch-Main.css")
            importStylesheet("/css/PodKatch-Light.css")
        }
    }
}

package podkatch.gui.error

import javafx.scene.control.Alert
import podkatch.gui.views.dialogs.AlertDialog
import tornadofx.*

/**
 * Alert the user of an error.
 * Display an error dialog and print the error message to the console.
 *
 * TODO: Also log the error.
 *
 * @param header The error dialog header.
 * @param message The error message.
 */
fun simpleErrorAlert(header: String, message: String?) {
    println("ERROR: $header ($message)")
    alert(Alert.AlertType.ERROR, header, message)
}

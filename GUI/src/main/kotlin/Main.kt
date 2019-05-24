package podkatch.gui

import tornadofx.*

import kodkatch.gui.views.MainView

fun main(args: Array<String>) {
    launch<MainApp>(args)
}

class MainApp: App(MainView::class)

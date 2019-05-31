package kodkatch.gui.views

import podkatch.gui.views.*
import tornadofx.*

class MainView : View("PodKatch Podcast Client") {
    override val root = borderpane {
        style {
            importStylesheet("/css/third-party/goliath/Goliath-Base.css")
            importStylesheet("/css/third-party/goliath/Goliath-Light.css")
            importStylesheet("/css/PodKatch-Main.css")
            importStylesheet("/css/PodKatch-Light.css")
        }
        val sidebar = left(Sidebar::class)
        val window = right(Window::class)

    }
}

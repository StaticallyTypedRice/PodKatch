package podcastengine.rss

import java.net.URL
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

fun getRemoteRSS(url: URL) {
    //TODO
}

fun getLocalRSS(path: String) {
    val RSSFile: InputStream = File(path)
    val RSSDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(RSSFile)

    RSSDocument.documentElement.normalize()

    return RSSDocument
}


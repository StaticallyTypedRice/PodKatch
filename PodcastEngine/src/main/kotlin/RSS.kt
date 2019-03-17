package podcastengine.rss

import java.net.URL
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document

fun getRemoteRSS(url: URL) {
    //TODO
}

fun getLocalRSS(path: String): Document {
    val RSSFile: File = File(path)
    val RSSDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(RSSFile)

    RSSDocument.documentElement.normalize()

    return RSSDocument
}


package podcastengine.rss

import java.net.URL
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import podcastengine.podcast.*

fun getRemoteRSS(url: URL) {
    //TODO
}

fun getLocalRSS(path: String): Document {
    val RSSFile: File = File(path)
    val RSSDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(RSSFile)

    RSSDocument.documentElement.normalize()

    return RSSDocument
}

fun createPodcastFromRSS(RSS: Document): Podcast {
    // Create a Podcast object from an RSS document

    // Parse the podcast metadata
    val channel: Element = RSS.getElementsByTagName("rss").item(0) as Element

    val podcast = Podcast()

    podcast.title = channel.getElementsByTagName("title").item(0).textContent
    podcast.link = URL(channel.getElementsByTagName("link").item(0).textContent)
    podcast.description = channel.getElementsByTagName("description").item(0).textContent
    podcast.image = URL(channel.getElementsByTagName("itunes:image").item(0).attributes.getNamedItem("href").textContent)
    podcast.author = channel.getElementsByTagName("itunes:author").item(0).textContent
    podcast.category = channel.getElementsByTagName("itunes:category").item(0).attributes.getNamedItem("text").textContent

    when(channel.getElementsByTagName("itunes:author").item(0).textContent) {
        "yes" -> podcast.itunesExplicit = true
        "no" -> podcast.itunesExplicit = false
    }

    podcast.language = channel.getElementsByTagName("language").item(0).textContent
    podcast.copyright = channel.getElementsByTagName("copyright").item(0).textContent

    // Parse the podcast episodes
    val episodes: NodeList = RSS.getElementsByTagName("item")
    for (i in 0 until episodes.length) {
         continue //TODO
    }

    return podcast
}

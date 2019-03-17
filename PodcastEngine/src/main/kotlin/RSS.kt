package podcastengine.rss

import java.net.URL
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.time.LocalDate
import java.time.Duration
import java.time.format.DateTimeFormatter
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

    // Parse the podcast RSS
    val channel: Element = RSS.getElementsByTagName("rss").item(0) as Element

    val podcast = Podcast()

    // Populate the podcast metadata
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
        val item: Element = episodes.item(i) as Element

        val episode = Episode(URL(item.getElementsByTagName("enclousre").item(0).attributes.getNamedItem("url").textContent))

        // Populate the episode metadata
        episode.guid = item.getElementsByTagName("guid").item(0).textContent
        episode.description = item.getElementsByTagName("description").item(0).textContent
        episode.pubDate = LocalDate.parse(item.getElementsByTagName("pubDate").item(0).textContent, DateTimeFormatter.ISO_DATE)
        episode.duration = Duration.parse(item.getElementsByTagName("itunes:duration").item(0).textContent)
        episode.episodeType = item.getElementsByTagName("itunes:episodeType").item(0).textContent

        when(item.getElementsByTagName("itunes:author").item(0).textContent) {
            "yes" -> episode.itunesExplicit = true
            "no" -> episode.itunesExplicit = false
        }

        episode.fileLength = item.getElementsByTagName("enclousre").item(0).attributes.getNamedItem("length").textContent.toLong()
        episode.fileType = item.getElementsByTagName("enclousre").item(0).attributes.getNamedItem("type").textContent

        // Append the episode to the podcast episode list
        podcast.episodes += episode

    }

    return podcast
}

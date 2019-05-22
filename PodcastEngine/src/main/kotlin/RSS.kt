package podcastengine.rss

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import java.net.URL
import java.io.File
import java.io.StringReader
import java.time.LocalDate
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilderFactory
import org.xml.sax.InputSource
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request

import podcastengine.podcast.*
import java.util.*

fun getRemoteRSS(url: URL): Request {

    // Use request.response { request, response, result -> parseRSS(response) } to parse the data.

    val request: Request = Fuel.get(url.toString())

    return request

}

fun getLocalRSS(path: String): File {
    val RSSFile = File(path)

    return RSSFile
}

fun parseRSS(rss: String): Document {
    val RSSString = InputSource(StringReader(rss))
    val RSSDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(RSSString)

    RSSDocument.documentElement.normalize()

    return RSSDocument
}
fun parseRSS(rss: ByteArray?): Document {
    val rssString: String

    when (rss) {
        null -> rssString = ""
        else -> rssString = String(rss)
    }

    val RSSInput = InputSource(StringReader(rssString))
    val RSSDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(RSSInput)

    RSSDocument.documentElement.normalize()

    return RSSDocument
}
fun parseRSS(rss: File): Document {
    val RSSDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rss)

    RSSDocument.documentElement.normalize()

    return RSSDocument
}

fun createPodcastFromRSS(RSS: Document): Podcast {
    // Create a Podcast object from an RSS document

    // Get the RSS channel element
    val channel: Element = RSS.getElementsByTagName("channel").item(0) as Element

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

        val title: String = item.getElementsByTagName("guid").item(0).textContent
        val file = URL(item.getElementsByTagName("enclosure").item(0).attributes.getNamedItem("url").textContent)

        val episode = Episode(title, file)

        // Populate the episode metadata
        episode.guid = item.getElementsByTagName("guid").item(0).textContent
        episode.description = item.getElementsByTagName("description").item(0).textContent
        episode.pubDate = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
                .parse(item.getElementsByTagName("pubDate").item(0).textContent)
        episode.duration = Duration.parse(item.getElementsByTagName("itunes:duration").item(0).textContent)
        episode.episodeType = item.getElementsByTagName("itunes:episodeType").item(0).textContent

        when(item.getElementsByTagName("itunes:author").item(0).textContent) {
            "yes" -> episode.itunesExplicit = true
            "no" -> episode.itunesExplicit = false
        }

        episode.fileLength = item.getElementsByTagName("enclosure").item(0).attributes.getNamedItem("length").textContent.toLong()
        episode.fileType = item.getElementsByTagName("enclosure").item(0).attributes.getNamedItem("type").textContent

        // Append the episode to the podcast episode list
        podcast.episodes += episode

    }

    return podcast
}

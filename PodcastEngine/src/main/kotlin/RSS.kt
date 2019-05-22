package podcastengine.rss

import java.net.URL
import java.io.File
import java.io.StringReader
import java.text.SimpleDateFormat
import java.time.Duration
import javax.xml.parsers.DocumentBuilderFactory
import org.xml.sax.InputSource
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request

import podcastengine.podcast.*
import java.util.*

fun getRemoteRss(url: URL): Request {

    // Use request.response { request, response, result -> parseRss(response) } to parse the data.

    val request: Request = Fuel.get(url.toString())

    return request

}

fun getLocalRss(path: String): File {
    val rssFile = File(path)

    return rssFile
}

fun parseRss(rss: String): Document {
    val rssInput = InputSource(StringReader(rss))
    val rssDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rssInput)

    rssDocument.documentElement.normalize()

    return rssDocument
}
fun parseRss(rss: ByteArray?): Document {
    val rssString: String

    when (rss) {
        null -> rssString = ""
        else -> rssString = String(rss)
    }

    val rssInput = InputSource(StringReader(rssString))
    val rssDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rssInput)

    rssDocument.documentElement.normalize()

    return rssDocument
}
fun parseRss(rss: File): Document {
    val rssDocument: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rss)

    rssDocument.documentElement.normalize()

    return rssDocument
}

fun parseDurationFromColonSeparatedTime(time: String): Duration {
    // Parse a string in the format HH:MM:SS to a Duration object

    val duration = Duration.ZERO

    val timeList = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    duration.plusHours(timeList[0].toLong())
    duration.plusMinutes(timeList[1].toLong())
    duration.plusSeconds(timeList[2].toLong())

    return duration
}

fun createPodcastFromRss(rss: Document): Podcast {
    // Create a Podcast object from an rss document

    // Get the rss channel element
    val channel: Element = rss.getElementsByTagName("channel").item(0) as Element

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
    val episodes: NodeList = rss.getElementsByTagName("item")
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
        episode.duration = parseDurationFromColonSeparatedTime(item.getElementsByTagName("itunes:duration").item(0).textContent)
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

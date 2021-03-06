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

import podcastengine.objects.*
import java.util.*

/**
 * Download an online RSS file.
 *
 * @param url The URL of the RSS file.
 */
fun getRemoteRss(url: URL): Request {

    val request: Request = Fuel.get(url.toString())

    return request

}

/**
 * Read a local RSS file.
 *
 * @param path The file path or File object of the RSS file.
 */
fun getLocalRss(path: String): File {
    val rssFile = File(path)
    return rssFile
}
fun getLocalRss(file: File): File {
    return file
}

/**
 * Parse RSS (XML) data into a Document object.
 *
 * @param rss A string, byte array or file object containing the RSS data.
 */
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

/**
 * Parses a colon separated timestamp to a Duration object.
 *
 * @param time A string in the format HH:MM:SS
 */
fun parseDurationFromColonSeparatedTime(time: String): Duration {

    val duration = Duration.ZERO

    val timeList = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    duration.plusHours(timeList[0].toLong())
    duration.plusMinutes(timeList[1].toLong())
    duration.plusSeconds(timeList[2].toLong())

    return duration
}

/**
 * Create a Podcast object from an rss document.
 *
 *  @param rss A Document object containing the RSS feed.
 *  @param source The podcast source (if applicable).
 */
fun createPodcastFromRss(rss: Document, source: PodcastSource? = null): Podcast {

    // Get the rss channel element
    val channel: Element = rss.getElementsByTagName("channel").item(0) as Element

    val podcast = Podcast()

    // Populate the podcast source if applicable
    if (source !== null) {
        podcast.source = source
    }

    // Populate the podcast metadata
    podcast.title = channel.getElementsByTagName("title").item(0).textContent
    podcast.link = URL(channel.getElementsByTagName("link").item(0).textContent)
    podcast.description = channel.getElementsByTagName("description").item(0).textContent
    podcast.image = URL(channel.getElementsByTagName("itunes:image").item(0).attributes.getNamedItem("href").textContent)
    podcast.author = channel.getElementsByTagName("itunes:author").item(0).textContent
    podcast.category = channel.getElementsByTagName("itunes:category").item(0).attributes.getNamedItem("text").textContent

    when(channel.getElementsByTagName("itunes:explicit").item(0).textContent) {
        "yes" -> podcast.itunesExplicit = true
        "no" -> podcast.itunesExplicit = false
        null -> podcast.itunesExplicit = false
        else -> podcast.itunesExplicit = false
    }

    podcast.language = channel.getElementsByTagName("language").item(0).textContent
    podcast.copyright = channel.getElementsByTagName("copyright").item(0).textContent

    podcast.episodes = createEpisodesFromRss(rss)

    return podcast
}

/**
 * Create a list of Episode objects from an rss document.
 *
 * @param rss A Document object containing the RSS feed.
 */
fun createEpisodesFromRss(rss: Document): Array<Episode> {
    val episodeRss: NodeList = rss.getElementsByTagName("item")
    var episodes = arrayOf<Episode>()

    for (i in 0 until episodeRss.length) {
        val item: Element = episodeRss.item(i) as Element

        val title: String = item.getElementsByTagName("title").item(0).textContent
        val file = URL(item.getElementsByTagName("enclosure").item(0).attributes.getNamedItem("url").textContent)

        val episode = Episode(title, file)

        // Populate the episode metadata
        episode.guid = item.getElementsByTagName("guid").item(0).textContent
        episode.description = item.getElementsByTagName("description").item(0).textContent
        episode.pubDate = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
                .parse(item.getElementsByTagName("pubDate").item(0).textContent)
        episode.duration = parseDurationFromColonSeparatedTime(item.getElementsByTagName("itunes:duration").item(0).textContent)
        episode.episodeType = item.getElementsByTagName("itunes:episodeType").item(0).textContent

        when(item.getElementsByTagName("itunes:explicit").item(0).textContent) {
            "yes" -> episode.itunesExplicit = true
            "no" -> episode.itunesExplicit = false
            null -> episode.itunesExplicit = false
            else -> episode.itunesExplicit = false
        }

        episode.fileLength = item.getElementsByTagName("enclosure").item(0).attributes.getNamedItem("length").textContent.toLong()
        episode.fileType = item.getElementsByTagName("enclosure").item(0).attributes.getNamedItem("type").textContent

        // Append the episode to the podcast episode list
        episodes += episode

    }

    return episodes
}
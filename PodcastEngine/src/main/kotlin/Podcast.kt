package podcastengine.podcast

import java.net.URL
import java.util.Date
import org.w3c.dom.Document


class Podcast(title: String, rss: Document) {
    var link: URL? = null               // The link to the podcast page
    var description: String? = null     // The podcast description
    var copyright: String? = null       // The copyright notice
    var image: URL? = null              // The cover image

    // All podcast episodes
    var episodes: Array<Episode> = arrayOf<Episode>()

    fun createFromRSS() {
        // Fill in the above data from an RSS file
        //TODO
    }
}

class Episode(file: URL) {
    var guid: String? = null        // The episode GUID
    var description: String? = null // The episode description
    var pubDate: Date? = null       // The episode publication date
    var duration: String? = null    // The episode duration (TODO: Use a dedicated data type)

    var fileLength:Long? = null     // The episode file length
    var fileType:String? = null     // The episode file type

    fun download(outputDir: String) {
        // Download the episode
        //TODO
    }
}

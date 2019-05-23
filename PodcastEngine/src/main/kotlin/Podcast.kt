package podcastengine.podcast

import java.net.URL
import java.time.Duration
import java.util.Date

import podcastengine.file.*

class Podcast {
    var title: String? = null           // The podcast title
    var link: URL? = null               // The link to the podcast page
    var description: String? = null     // The podcast description
    var image: URL? = null              // The cover image
    var author: String? = null          // The podcast author
    var category: String? = null        // The podcast category

    // Whether the podcast is set as explicit for iTunes
    // Set by the author, not iTunes
    // Doesn't prove that the podcast isn't explicit if false or null
    /// Not guaranteed to be an accurate indicator, not sure if moderated by iTunes (probably not)
    var itunesExplicit: Boolean? = null

    var language: String? = null        // The podcast language
    var copyright: String? = null       // The copyright notice

    // All podcast episodes
    var episodes: Array<Episode> = arrayOf()
}

class Episode(_title: String, _url: URL) {
    val title: String = _title      // The episode title
    val url: URL = _url             // The episode url
    val file: FileName = parseFilenameFromUrl(_url) // The Episode file name

    var guid: String? = null        // The episode GUID
    var description: String? = null // The episode description
    var pubDate: Date? = null       // The episode publication date
    var duration: Duration? = null  // The episode duration
    var episodeType: String? = null // The episode type (based on iTunes' episode types)

    // Whether the episode is set as explicit for iTunes
    // Set by the author, not iTunes
    // Doesn't prove that the episode isn't explicit if false or null
    // Not guaranteed to be an accurate indicator, not sure if moderated by iTunes (probably not)
    var itunesExplicit: Boolean? = null

    var fileLength:Long? = null     // The episode file length
    var fileType:String? = null     // The episode file type

    /**
     * Download the episode.
     *
     * @param outputDir The path of the directory to download the file to.
     */
    fun download(outputDir: String) {

        println("TODO: Download '$title' to '$outputDir'.")
    }
}

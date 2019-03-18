package podcastengine.podcast

import java.net.URL
import java.time.LocalDate
import java.time.Duration

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

class Episode(title: String, file: URL) {
    var guid: String? = null        // The episode GUID
    var description: String? = null // The episode description
    var pubDate: LocalDate? = null  // The episode publication date
    var duration: Duration? = null  // The episode duration
    var episodeType: String? = null // The episode type (based on iTunes' episode types)

    // Whether the podcast is set as explicit for iTunes
    // Set by the author, not iTunes
    // Doesn't prove that the podcast isn't explicit if false or null
    // Not guaranteed to be an accurate indicator, not sure if moderated by iTunes (probably not)
    var itunesExplicit: Boolean? = null

    var fileLength:Long? = null     // The episode file length
    var fileType:String? = null     // The episode file type

    fun download(outputDir: String) {
        // Download the episode
        //TODO
    }
}

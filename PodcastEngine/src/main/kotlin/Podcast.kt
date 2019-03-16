import java.net.URL
import java.util.Date

class Podcast {
    var title: String           // The podcast title
    var rss: URL                // The podcast RSS URL
    var link: URL?              // The link to the podcast page
    var description: String?    // The podcast description
    var copyright: String?      // The copyright notice
    var image: URL?             // The cover image

    var episodes: Array<Episode>
        get() = field
        set(value) {
            field = value
        }

    fun createFromRSS(file: String) {
        // Fill in the above data from an RSS file
        //TODO
    }
}

class Episode {
    var guid: String?           // The episode GUID
    var description: String?    // The episode description
    var pubDate: Date?          // The epidode publication date
    var duration: String?       // The epidode duration (TODO: Use a dedicated data type)

    var file: URL               // The episode file URL
    var fileLength:Long?        // The episode file length
    var fileType:String?        // The episode file type

    fun download(outputDir: String) {
        // Download the episode
        //TODO
    }
}

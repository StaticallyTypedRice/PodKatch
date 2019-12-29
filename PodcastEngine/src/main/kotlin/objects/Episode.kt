package podcastengine.objects

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import podcastengine.file.FileName
import podcastengine.file.parseFilenameFromUrl
import java.net.URL
import java.time.Duration
import java.util.*

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
     */
    fun download(): Request {
        return Fuel.get(url.toString())
    }
}

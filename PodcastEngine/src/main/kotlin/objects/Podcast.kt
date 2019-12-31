package podcastengine.objects

import java.net.URL

class Podcast {
    var dbId: Int? = null               // The database ID
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

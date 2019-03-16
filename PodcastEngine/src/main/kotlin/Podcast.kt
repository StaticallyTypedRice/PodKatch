import java.net.URL

package podcastengine

class Podcast {
    var title: String           // The podcast title
    var rss: URL                // The podcast RSS URL
    var link: URL?              // The link to the podcast page
    var description: String?    // The podcast description
    var copyright: String?      // The copyright notice
    var image: URL?             // The cover image
}

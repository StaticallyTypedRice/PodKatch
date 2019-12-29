package podcastengine.subscription

import org.w3c.dom.Document

import podcastengine.rss.createPodcastFromRss

/**
 * Subscribe to a podcast given an RSS Document.
 *
 * @param rss The podcast RSS Document.
 */
fun subscribe(rss: Document) {
    val podcast = createPodcastFromRss(rss)

    println("TODO: Store subscription information in a database.")
}

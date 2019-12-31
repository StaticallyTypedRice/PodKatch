package podcastengine.subscription

import org.w3c.dom.Document

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import podcastengine.database.connectDb
import podcastengine.database.initializeDb

import podcastengine.database.tables.*
import podcastengine.objects.*
import podcastengine.rss.createPodcastFromRss

/**
 * Subscribe to a podcast given an RSS Document.
 *
 * @param rss The podcast RSS Document.
 * @param source The podcast source (if applicable).
 */
fun subscribe(rss: Document, source: PodcastSource? = null) {
    val podcast = createPodcastFromRss(rss, source)

    connectDb()
    initializeDb()

    addPodcastToDb(podcast)
}

/**
 * Add the podcast and optionally episode data to the database.
 *
 * @param podcast The podcast to add.
 * @param addEpisodes Whether to also add the podcast episodes.
 */
fun addPodcastToDb(podcast: Podcast, addEpisodes: Boolean = true): Podcast {
    var podcastNew = podcast

    var podcastDbId: Int? = null

    transaction {
        podcastDbId = PodcastTable.insert {
            //it[title] = podcast.title
            // TODO

        } get PodcastTable.id
    }

    podcastNew.dbId = podcastDbId

    if (addEpisodes) {
        podcastNew = addEpisodesToDb(podcast)
    }

    return podcastNew
}

/**
 * Add the episode data of a podcast to the database.
 *
 * @param podcast The podcast whose episodes to add.
 */
fun addEpisodesToDb(podcast: Podcast): Podcast {
    transaction {
        val episodeDbId = EpisodeTable.insert {

        } get EpisodeTable.id
    }

    return podcast
}

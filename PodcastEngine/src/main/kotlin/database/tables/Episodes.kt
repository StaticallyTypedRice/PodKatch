package podcastengine.database.tables

import org.jetbrains.exposed.sql.*

object Episodes : Table() {
    val id = integer("id").autoIncrement().index()                 // The episode unique ID
    val title = varchar("title", 1000)                               // The episode source
    val src = varchar("src", 1000)                                   // The episode GUID
    val guid = varchar("guid", 100).nullable()                       // The podcast description
    val description = varchar("description", 100000).nullable()      // The episode description
    val type = varchar("type", 100).nullable()                       // The podcast type

    // Whether the episode is set as explicit for iTunes
    // Set by the author, not iTunes
    // Doesn't prove that the episode isn't explicit if false or null
    // Not guaranteed to be an accurate indicator, not sure if moderated by iTunes (probably not)
    // 0 is false, 1 is true
    val itunesExplicit = integer("itunesExplicit").nullable()

    val downloaded = integer("downloaded")                                  // Whether the episode has been downloaded
    val path = varchar("path", 1000).nullable()                      // The path to the local episode file
}

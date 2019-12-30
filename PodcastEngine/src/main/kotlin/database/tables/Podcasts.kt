package database.tables

import org.jetbrains.exposed.sql.*

object Podcasts : Table() {
    val id = integer("id").autoIncrement().index()                         // The podcast unique ID
    val title = varchar("title", 1000)                              // The podcast title
    val src = varchar("src", 1000)                                  // The podcast source
    val link = varchar("link", 1000).nullable()                     // The link to the podcast page
    val description = varchar("description", 100000).nullable()     // The podcast description
    val image = varchar("image", 1000).nullable()                   // The cover image
    val author = varchar("author", 100).nullable()                  // The podcast author
    val category = varchar("category", 100).nullable()              // The podcast category

    // Whether the podcast is set as explicit for iTunes
    // Set by the author, not iTunes
    // Doesn't prove that the podcast isn't explicit if false or null
    // Not guaranteed to be an accurate indicator, not sure if moderated by iTunes (probably not)
    // 0 is false, 1 is true
    val itunesExplicit = integer("itunesExplicit").nullable()

    val lang = varchar("lang", 10).nullable()                       // The podcast language
    val copyright = varchar("copyright", 1000).nullable()           // The podcast copyright notice
}

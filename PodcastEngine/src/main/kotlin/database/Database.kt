package podcastengine.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

import podcastengine.database.tables.*



/**
 * Connect to the database.
 */
fun connectDb() {
    Database.connect("jdbc:h2:/tmp/main.db", driver = "org.h2.Driver", user = "root", password = "")
}

/**
 * Create database tables.
 */
fun initializeDb() {
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Podcasts, Episodes)
    }
}

/**
 * Used for testing the database configuration.
 * Do not call in the app code.
 */
private fun main() {
    connectDb()
    initializeDb()
}

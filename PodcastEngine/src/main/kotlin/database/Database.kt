package podcastengine.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

import podcastengine.database.tables.*

// Database credentials
val DB_LOCATION = "/tmp/main.db"
val DB_URL = "jdbc:h2:$DB_LOCATION"
val DB_DRIVER = "org.h2.Driver"
val DB_USER = "root"
val DB_PASSWORD = ""

/**
 * Connect to the database.
 */
fun connectDb(): Database {
    return Database.connect(DB_URL, driver = DB_DRIVER, user = DB_USER, password = DB_PASSWORD)

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

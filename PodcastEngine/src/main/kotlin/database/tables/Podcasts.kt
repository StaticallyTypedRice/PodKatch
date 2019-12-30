package database.tables

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Podcasts : Table() {
    val id = varchar("id", 10) // Column<String>

}
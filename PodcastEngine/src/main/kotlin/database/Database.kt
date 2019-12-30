package podcastengine.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver.Companion.IN_MEMORY

//import podcastengine.database.MainDatabase

val driver: SqlDriver = JdbcSqliteDriver(IN_MEMORY)

//MainDatabase.Schema.create(driver)

//val database = MainDatabase(driver)

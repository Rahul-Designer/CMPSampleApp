package com.company.coreDatabase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual class SqlDriverFactory actual constructor(context : Any?) {

    actual fun getSqlDriver() : SqlDriver{
        val driver = JdbcSqliteDriver(
            "jdbc:sqlite:AppDatabase.db"
        )

        try {
            AppDatabase.Schema.create(driver)
        } catch (e: Exception) {
            // Schema already exists
        }

        return driver
    }
}
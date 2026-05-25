package com.company.coreDatabase

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class SqlDriverFactory actual constructor(context : Any?) {

    private val androidContext = context as Context

    actual fun getSqlDriver() : SqlDriver {
        return AndroidSqliteDriver(
            AppDatabase.Schema,
            androidContext,
            "AppDataBase.db"
        )
    }
}
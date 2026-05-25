package com.company.coreDatabase.di

import app.cash.sqldelight.db.SqlDriver
import com.company.coreDatabase.AppDatabase
import com.company.coreDatabase.SqlDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getCoreDatabaseModule(): Module {
   return module {
       single { SqlDriverFactory().getSqlDriver() }
       single { AppDatabase.invoke(get<SqlDriver>()) }
   }
}
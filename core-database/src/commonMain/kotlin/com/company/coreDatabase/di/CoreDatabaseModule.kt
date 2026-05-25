package com.company.coreDatabase.di

import com.company.coreDatabase.SqlDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun getCoreDatabaseModule() : Module
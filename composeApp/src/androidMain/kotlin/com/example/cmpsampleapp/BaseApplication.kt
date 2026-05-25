package com.example.cmpsampleapp

import android.app.Application
import com.example.cmpsampleapp.di.initKoin
import org.koin.dsl.module

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            it.modules(
                module {
                    single<android.content.Context> {
                        this@BaseApplication.applicationContext
                    }
                }
            )
        }
    }
}
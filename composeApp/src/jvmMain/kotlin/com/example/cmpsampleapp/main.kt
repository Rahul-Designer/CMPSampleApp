package com.example.cmpsampleapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.cmpsampleapp.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMPSampleApp",
    ) {
        initKoin()
        App()
    }
}
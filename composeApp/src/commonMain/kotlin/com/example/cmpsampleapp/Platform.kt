package com.example.cmpsampleapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
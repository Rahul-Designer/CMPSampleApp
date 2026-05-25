package com.company.coreNetwork.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {

//    https://api.rawg.io/api/games?key=1abb1867f52548a4aa9f54dd4946af2f

    fun getInstance(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
//        install(Logging) {
//            logger = object : Logger {
//                override fun log(message: String) {
//                    println("Ktor => $message")
//                }
//            }
//            level = LogLevel.ALL
//        }

        install(DefaultRequest) {
            url {
                host = "api.rawg.io"
                protocol = URLProtocol.HTTPS
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }

        install(HttpTimeout) {
            socketTimeoutMillis = 5000
            connectTimeoutMillis = 5000
            requestTimeoutMillis = 5000
        }
    }
}
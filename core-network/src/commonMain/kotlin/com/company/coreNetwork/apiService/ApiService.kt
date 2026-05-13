package com.company.coreNetwork.apiService

import com.company.coreNetwork.model.game.GameResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(
    val httpClient: HttpClient
) {
    //    https://api.rawg.io/api/games?key=1abb1867f52548a4aa9f54dd4946af2f
    suspend fun getGames(): Result<GameResponse> {
        return try {
            val response = httpClient.get("api/games") {
                url {
                    parameter("keys", "1abb1867f52548a4aa9f54dd4946af2f")
                }

            }.body<GameResponse>()

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
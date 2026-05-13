package com.company.game.data.repository

import com.company.coreNetwork.apiService.ApiService
import com.company.game.data.mappers.toDomainListOfGames
import com.company.game.domain.model.Game
import com.company.game.domain.repository.GameRepository

class GameRepositoryImpl(private val apiService: ApiService) : GameRepository {
    override suspend fun getGames(): Result<List<Game>> {
        val result = apiService.getGames()
        return if (result.isSuccess) {
            Result.success(result.getOrThrow().results.toDomainListOfGames())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }
}
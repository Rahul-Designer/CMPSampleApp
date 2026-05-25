package com.company.game.data.repository

import com.company.common.data.mappers.toDomainListOfGames
import com.company.common.domain.model.Game
import com.company.coreDatabase.AppDatabase
import com.company.coreNetwork.apiService.ApiService
import com.company.game.data.mappers.toDomainGameDetails
import com.company.game.domain.model.GameDetails
import com.company.game.domain.repository.GameRepository

class GameRepositoryImpl(private val apiService: ApiService, private val appDatabase: AppDatabase) :
    GameRepository {
    override suspend fun getGames(): Result<List<Game>> {
        val result = apiService.getGames()
        return if (result.isSuccess) {
            Result.success(result.getOrThrow().results.toDomainListOfGames())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }

    override suspend fun getGameDetails(id: Int): Result<GameDetails> {
        val result = apiService.getDetails(id)
        return if (result.isSuccess) {
            Result.success(result.getOrThrow().toDomainGameDetails())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }

    override suspend fun onSave(id: Int, name: String, image: String) {
        appDatabase.appDatabaseQueries.upsert(id.toLong(), name, image)
    }

    override suspend fun onDelete(id: Int) {
        appDatabase.appDatabaseQueries.delete(id.toLong())
    }
}
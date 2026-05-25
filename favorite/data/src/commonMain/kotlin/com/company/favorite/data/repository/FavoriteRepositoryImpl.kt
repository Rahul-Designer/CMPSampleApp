package com.company.favorite.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.company.common.domain.model.Game
import com.company.coreDatabase.AppDatabase
import com.company.favorite.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val database: AppDatabase,
) : FavoriteRepository {
    override fun getAllGames(): Flow<List<Game>> {
        return database.appDatabaseQueries
            .getAllGames()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map {
                it.map {
                    Game(id = it.id.toInt(), name = it.name, imageUrl = it.image)
                }
            }

    }

    override suspend fun upsert(id: Int, name: String, image: String) {
        database.appDatabaseQueries
            .upsert(id = id.toLong(), name = name, image = image)
    }

    override suspend fun delete(id: Int) {
        database.appDatabaseQueries.delete(id = id.toLong())
    }
}
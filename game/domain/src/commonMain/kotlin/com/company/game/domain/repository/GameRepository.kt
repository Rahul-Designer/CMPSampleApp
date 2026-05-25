package com.company.game.domain.repository

import com.company.common.domain.model.Game
import com.company.game.domain.model.GameDetails

interface GameRepository {

    suspend fun getGames(): Result<List<Game>>

    suspend fun getGameDetails(id: Int): Result<GameDetails>

    suspend fun onSave(id: Int, name: String, image: String)

    suspend fun onDelete(id: Int)
}
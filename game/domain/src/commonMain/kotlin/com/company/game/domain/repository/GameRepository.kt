package com.company.game.domain.repository

import com.company.game.domain.model.Game

interface GameRepository {

    suspend fun getGames() : Result<List<Game>>
}
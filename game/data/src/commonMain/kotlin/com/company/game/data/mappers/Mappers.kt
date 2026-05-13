package com.company.game.data.mappers

import com.company.coreNetwork.model.game.Result
import com.company.game.domain.model.Game

fun List<Result>.toDomainListOfGames(): List<Game> = map {
    Game(
        id = it.id,
        name = it.name,
        imageUrl = it.background_image
    )
}
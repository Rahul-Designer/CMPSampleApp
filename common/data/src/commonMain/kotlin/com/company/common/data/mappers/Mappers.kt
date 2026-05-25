package com.company.common.data.mappers

import com.company.common.domain.model.Game

fun List<com.company.coreNetwork.model.game.Result>.toDomainListOfGames(): List<Game> = map {
    Game(
        id = it.id,
        name = it.name,
        imageUrl = it.background_image
    )
}
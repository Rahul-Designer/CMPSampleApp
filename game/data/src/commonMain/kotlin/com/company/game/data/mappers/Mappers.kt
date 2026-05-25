package com.company.game.data.mappers

import com.company.coreNetwork.model.gameDetails.GameDetailsResponse
import com.company.game.domain.model.Developer
import com.company.game.domain.model.GameDetails
import com.company.game.domain.model.Platform
import com.company.game.domain.model.Store
import com.company.game.domain.model.Tag

fun GameDetailsResponse.toDomainGameDetails(): GameDetails {
    return GameDetails(
        id = id,
        name = name,
        description = description,
        backgroundImage = background_image,
        additionalImage = background_image_additional,
        platforms = platforms.map {
            Platform(
                name = it.platform.name,
                image = it.platform.image_background
            )
        },
        stores = stores.map {
            Store(
                name = it.store.name,
                image = it.store.image_background,
                domain = it.store.domain,
                gameCount = it.store.games_count
            )
        },
        developers = developers.map {
            Developer(
                name = it.name,
                image = it.image_background,
                gameCount = it.games_count
            )
        },
        tags = tags.map {
            Tag(
                name = it.name,
                image = it.image_background,
                gameCount = it.games_count
            )
        },
    )
}
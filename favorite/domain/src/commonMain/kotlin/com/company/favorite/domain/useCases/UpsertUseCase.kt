package com.company.favorite.domain.useCases

import com.company.favorite.domain.repository.FavoriteRepository

class UpsertUseCase(private val favoriteRepository: FavoriteRepository) {

    suspend operator fun invoke(id: Int, name: String, image: String) =
        favoriteRepository.upsert(id, name, image)
}
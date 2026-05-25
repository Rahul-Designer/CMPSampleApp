package com.company.favorite.domain.useCases

import com.company.favorite.domain.repository.FavoriteRepository

class GetAllLocalCacheGameUseCase(private val favoriteRepository: FavoriteRepository) {

    operator fun invoke() = favoriteRepository.getAllGames()

}
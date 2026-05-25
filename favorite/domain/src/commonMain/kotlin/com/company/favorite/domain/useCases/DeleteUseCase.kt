package com.company.favorite.domain.useCases

import com.company.favorite.domain.repository.FavoriteRepository

class DeleteUseCase(private val favoriteRepository: FavoriteRepository) {

    suspend operator fun invoke(id: Int) = favoriteRepository.delete(id = id)
}
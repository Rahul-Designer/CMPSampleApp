package com.company.game.domain.useCases

import com.company.game.domain.repository.GameRepository

class DeleteUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(id: Int) {
        gameRepository.onDelete(id)
    }
}
package com.company.game.domain.useCases

import com.company.game.domain.repository.GameRepository

class SaveGameUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(id: Int, name: String, image: String) {
        gameRepository.onSave(id, name, image)
    }
}
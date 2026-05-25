package com.company.game.domain.useCases

import com.company.game.domain.model.GameDetails
import com.company.game.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGameDetailUseCase(private val gameRepository: GameRepository) {
    operator fun invoke(id: Int) = flow<Result<GameDetails>> {
        emit(gameRepository.getGameDetails(id))
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.IO)
}
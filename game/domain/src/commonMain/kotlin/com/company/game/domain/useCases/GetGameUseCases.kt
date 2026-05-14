package com.company.game.domain.useCases

import com.company.game.domain.model.Game
import com.company.game.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGameUseCases(private val gameRepository: GameRepository) {
    operator fun invoke() = flow<Result<List<Game>>> {
        emit(gameRepository.getGames())
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.IO)
}
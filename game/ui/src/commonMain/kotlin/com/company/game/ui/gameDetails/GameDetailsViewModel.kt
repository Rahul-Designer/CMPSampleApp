package com.company.game.ui.gameDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.game.domain.model.GameDetails
import com.company.game.domain.useCases.DeleteUseCase
import com.company.game.domain.useCases.GetGameDetailUseCase
import com.company.game.domain.useCases.SaveGameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameDetailsViewModel(
    private val gameDetailUseCase: GetGameDetailUseCase,
    private val saveGameUseCase: SaveGameUseCase,
    private val deleteUseCase: DeleteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameDetailsScreen.UiState())
    val uiState: StateFlow<GameDetailsScreen.UiState> = _uiState.asStateFlow()

    fun getGameDetails(id: Int) {
        gameDetailUseCase.invoke(id)
            .onStart {
                _uiState.update { GameDetailsScreen.UiState(isLoading = true) }
            }
            .onEach { result ->
                result.onSuccess { data ->
                    _uiState.update { GameDetailsScreen.UiState(date = data) }
                }.onFailure { error ->
                    _uiState.update { GameDetailsScreen.UiState(error = error.message.toString()) }

                }
            }.launchIn(viewModelScope)

    }

    fun save(id: Int, name: String, image: String) {
        viewModelScope.launch { saveGameUseCase.invoke(id, name, image) }
    }

    fun delete(id: Int) {
        viewModelScope.launch { deleteUseCase.invoke(id) }
    }
}

object GameDetailsScreen {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val date: GameDetails? = null
    )

}
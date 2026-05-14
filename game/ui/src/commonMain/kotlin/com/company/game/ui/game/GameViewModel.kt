package com.company.game.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.game.domain.model.Game
import com.company.game.domain.useCases.GetGameUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class GameViewModel(private val getGameUseCases: GetGameUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(GameScreen.UiState())
    val uiState: StateFlow<GameScreen.UiState> = _uiState.asStateFlow()

    init {
        getGame()
    }


    fun getGame() {
        getGameUseCases.invoke()
            .onStart {
                _uiState.update {
                    GameScreen.UiState(isLoading = true)
                }

            }
            .onEach { result ->
                result.onSuccess { data ->
                    _uiState.update { GameScreen.UiState(data = data) }
                }.onFailure { error ->
                    _uiState.update { GameScreen.UiState(error = error.message.toString()) }

                }
            }.launchIn(viewModelScope)
    }

}

object GameScreen {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<Game>? = null,
    )
}
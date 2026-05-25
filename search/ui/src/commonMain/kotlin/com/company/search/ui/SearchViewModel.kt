package com.company.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.common.domain.model.Game
import com.company.search.data.useCases.SearchGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(private val searchGamesUseCase: SearchGamesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreen.UiState())
    val uiState: StateFlow<SearchScreen.UiState> = _uiState.asStateFlow()

    private var _query = MutableStateFlow("")

    fun updateQuery(q: String) {
        _query.update { q }
    }

    init {
        viewModelScope.launch {
            _query.filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(500)
                .collectLatest { query ->
                    search(query)
                }
        }
    }

    private fun search(q: String) {
        searchGamesUseCase.invoke(q)
            .onStart {
                _uiState.update { SearchScreen.UiState(isLoading = true) }
            }
            .onEach { result ->
                result.onSuccess { data ->
                    _uiState.update { SearchScreen.UiState(data = data) }
                }.onFailure { error ->
                    _uiState.update { SearchScreen.UiState(error = error.message.toString()) }

                }

            }
            .launchIn(viewModelScope)
    }
}

data object SearchScreen {

    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<Game>? = null
    )
}
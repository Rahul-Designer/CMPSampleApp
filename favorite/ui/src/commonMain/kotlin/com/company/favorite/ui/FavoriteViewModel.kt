package com.company.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.favorite.domain.useCases.DeleteUseCase
import com.company.favorite.domain.useCases.GetAllLocalCacheGameUseCase
import com.company.favorite.domain.useCases.UpsertUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getAllLocalCacheGameUseCase: GetAllLocalCacheGameUseCase,
    private val upsertUseCase: UpsertUseCase,
    private val deleteUseCase: DeleteUseCase
) : ViewModel() {


    val games = getAllLocalCacheGameUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
    )

    fun delete(id: Int) {
        viewModelScope.launch {
            deleteUseCase.invoke(id)
        }
    }
}
package com.company.game.ui.di

import com.company.game.ui.game.GameViewModel
import com.company.game.ui.gameDetails.GameDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getGameUiModule() = module {
    viewModel {
        GameViewModel(getGameUseCases = get())
    }

    viewModel {
        GameDetailsViewModel(
            gameDetailUseCase = get(),
            saveGameUseCase = get(),
            deleteUseCase = get()
        )
    }
}
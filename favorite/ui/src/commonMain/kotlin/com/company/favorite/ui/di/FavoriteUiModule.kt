package com.company.favorite.ui.di

import com.company.favorite.ui.FavoriteViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getFavoriteUiModule() : Module {
    return module {
        viewModel {
            FavoriteViewModel(
                getAllLocalCacheGameUseCase = get(),
                deleteUseCase = get(),
            )
        }
    }
}
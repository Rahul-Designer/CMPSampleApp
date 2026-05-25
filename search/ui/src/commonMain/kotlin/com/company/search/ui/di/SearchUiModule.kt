package com.company.search.ui.di

import com.company.search.ui.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getSearchUiModule() = module {
    viewModel {
        SearchViewModel(searchGamesUseCase = get())
    }
}
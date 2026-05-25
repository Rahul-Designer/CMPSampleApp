package com.company.search.data.di

import com.company.search.data.useCases.SearchGamesUseCase
import org.koin.dsl.module

fun getSearchDomainModule() = module{
    factory<SearchGamesUseCase> {
        SearchGamesUseCase(searchRepository = get())
    }
}
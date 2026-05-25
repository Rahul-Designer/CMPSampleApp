package com.company.search.data.di

import com.company.search.data.repository.SearchRepository
import com.company.search.data.repository.SearchRepositoryImpl
import org.koin.dsl.module

fun getSearchDataModule() = module {
    factory<SearchRepository> {
        SearchRepositoryImpl(apiService = get())
    }
}
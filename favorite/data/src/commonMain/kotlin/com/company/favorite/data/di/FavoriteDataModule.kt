package com.company.favorite.data.di

import com.company.coreDatabase.AppDatabase
import com.company.favorite.data.repository.FavoriteRepositoryImpl
import com.company.favorite.domain.repository.FavoriteRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getFavoriteDataModel() : Module {
    return module {
        factory<FavoriteRepository> {
            FavoriteRepositoryImpl(get<AppDatabase>())
        }
    }
}
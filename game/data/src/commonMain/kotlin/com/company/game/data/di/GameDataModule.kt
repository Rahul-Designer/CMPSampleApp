package com.company.game.data.di

import com.company.coreDatabase.AppDatabase
import com.company.coreNetwork.apiService.ApiService
import com.company.game.data.repository.GameRepositoryImpl
import com.company.game.domain.repository.GameRepository
import org.koin.dsl.module

fun getGameDataModule() = module {
    factory<GameRepository> {
        GameRepositoryImpl(apiService = get<ApiService>(), appDatabase = get<AppDatabase>())
    }
}

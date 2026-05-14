package com.company.game.domain.di

import com.company.game.domain.useCases.GetGameUseCases
import org.koin.dsl.module

fun getGameDomainModule() = module {
    factory {
        GetGameUseCases(gameRepository = get())
    }
}
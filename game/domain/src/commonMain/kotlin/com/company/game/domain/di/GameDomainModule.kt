package com.company.game.domain.di

import com.company.game.domain.useCases.DeleteUseCase
import com.company.game.domain.useCases.GetGameDetailUseCase
import com.company.game.domain.useCases.GetGameUseCases
import com.company.game.domain.useCases.SaveGameUseCase
import org.koin.dsl.module

fun getGameDomainModule() = module {
    factory {
        GetGameUseCases(gameRepository = get())
    }
    factory {
        GetGameDetailUseCase(gameRepository = get())
    }
    factory {
        SaveGameUseCase(gameRepository = get())
    }
    factory {
        DeleteUseCase(gameRepository = get())
    }
}
package com.company.favorite.domain.di

import com.company.favorite.domain.useCases.DeleteUseCase
import com.company.favorite.domain.useCases.GetAllLocalCacheGameUseCase
import com.company.favorite.domain.useCases.UpsertUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getFavoriteDomainModule(): Module {
    return module {
        factory {
            DeleteUseCase(get())
        }
        factory {
            GetAllLocalCacheGameUseCase(get())
        }
        factory {
            UpsertUseCase(get())
        }
    }
}
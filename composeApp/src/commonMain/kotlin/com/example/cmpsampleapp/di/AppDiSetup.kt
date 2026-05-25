package com.example.cmpsampleapp.di

import com.company.coreDatabase.di.getCoreDatabaseModule
import com.company.coreNetwork.di.getCoreNetworkModule
import com.company.favorite.data.di.getFavoriteDataModel
import com.company.favorite.domain.di.getFavoriteDomainModule
import com.company.favorite.ui.di.getFavoriteUiModule
import com.company.game.data.di.getGameDataModule
import com.company.game.domain.di.getGameDomainModule
import com.company.game.ui.di.getGameUiModule
import com.company.search.data.di.getSearchDataModule
import com.company.search.data.di.getSearchDomainModule
import com.company.search.ui.di.getSearchUiModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        koinApplication?.invoke(this)
        modules(
            getCoreNetworkModule(),
            getCoreDatabaseModule(),
            getGameDataModule(),
            getGameDomainModule(),
            getGameUiModule(),
            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),
            getFavoriteDataModel(),
            getFavoriteDomainModule(),
            getFavoriteUiModule()
        )
    }
}

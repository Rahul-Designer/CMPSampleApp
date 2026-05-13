package com.company.coreNetwork.di

import com.company.coreNetwork.apiService.ApiService
import com.company.coreNetwork.client.KtorClient
import org.koin.dsl.module

fun getCoreNetworkModule() = module {
    single {
        ApiService(httpClient = KtorClient.getInstance())
    }
}
package com.company.search.data.repository

import com.company.common.data.mappers.toDomainListOfGames
import com.company.common.domain.model.Game
import com.company.coreNetwork.apiService.ApiService

class SearchRepositoryImpl(private val apiService: ApiService) : SearchRepository {
    override suspend fun search(q: String): Result<List<Game>> {
        val response = apiService.search(q)
        return if (response.isSuccess) {
            Result.success(response.getOrThrow().results.toDomainListOfGames())
        } else {
            Result.failure(response.exceptionOrNull()!!)
        }
    }
}
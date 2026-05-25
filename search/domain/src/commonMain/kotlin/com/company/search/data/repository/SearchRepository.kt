package com.company.search.data.repository

import com.company.common.domain.model.Game

interface SearchRepository {
    suspend fun search(q: String) : Result<List<Game>>
}
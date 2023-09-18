package com.example.shacklehotelbuddy.search.usecase

import com.example.shacklehotelbuddy.data.repository.SearchRepository
import com.example.shacklehotelbuddy.search.model.SearchQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecentSearchesUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(): Flow<List<SearchQuery>> = flow {
        emit(
            repository.getSearchQueries()
        )
    }
}
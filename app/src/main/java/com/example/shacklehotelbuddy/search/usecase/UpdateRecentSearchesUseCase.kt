package com.example.shacklehotelbuddy.search.usecase

import com.example.shacklehotelbuddy.data.repository.SearchRepository
import com.example.shacklehotelbuddy.search.model.SearchQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateRecentSearchesUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(searchQuery: SearchQuery): Flow<List<SearchQuery>> {
        repository.insertSearchQuery(searchQuery)

        return flow { emit(repository.getSearchQueries()) }
    }
}
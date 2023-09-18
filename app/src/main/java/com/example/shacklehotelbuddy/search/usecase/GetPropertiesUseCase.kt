package com.example.shacklehotelbuddy.search.usecase

import com.example.shacklehotelbuddy.data.repository.SearchRepository
import com.example.shacklehotelbuddy.data.repository.model.PropertiesResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPropertiesUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(
        adults: Int,
        children: Int,
        checkInDate: Long,
        checkOutDate: Long,
    ): Flow<PropertiesResult> = flow {
        emit(repository.getProperties(checkInDate, checkOutDate, adults, children))
    }
}


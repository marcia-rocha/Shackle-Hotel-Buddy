package com.example.shacklehotelbuddy.data.repository

import com.example.shacklehotelbuddy.data.repository.model.PropertiesResult
import com.example.shacklehotelbuddy.search.model.SearchQuery

const val DEFAULT_CURRENCY = "USD"
const val DEFAULT_LOCALE = "en_US"
const val DEFAULT_RESULT_SIZE = 200
const val DEFAULT_RESULT_STARTING_INDEX = 0
const val DEFAULT_CHILDREN_AGE = 7
const val DEFAULT_REGION_ID = "6054439"
const val DEFAULT_PRICE_SORT = "PRICE_LOW_TO_HIGH"
const val DEFAULT_EAP_ID = 1
const val DEFAULT_SITE_ID = 300000001L
const val DEFAULT_MAX_PRICE = 1500.0
const val DEFAULT_MIN_PRICE = 1.0

interface SearchRepository {
    suspend fun getProperties(
        checkInDateMillis: Long,
        checkOutDateMillis: Long,
        adults: Int,
        children: Int
    ): PropertiesResult
    suspend fun getSearchQueries(): List<SearchQuery>
    suspend fun insertSearchQuery(query: SearchQuery)
}
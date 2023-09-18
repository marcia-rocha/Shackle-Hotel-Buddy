package com.example.shacklehotelbuddy.data.repository


import androidx.room.withTransaction
import com.example.shacklehotelbuddy.commoncore.getCurrentYear
import com.example.shacklehotelbuddy.commoncore.getDayOfMonth
import com.example.shacklehotelbuddy.commoncore.getMonthOfYear
import com.example.shacklehotelbuddy.data.local.ShackleDatabase
import com.example.shacklehotelbuddy.data.local.mapper.toSearchQuery
import com.example.shacklehotelbuddy.data.local.mapper.toSearchQueryEntity
import com.example.shacklehotelbuddy.data.local.retrofitApiCall
import com.example.shacklehotelbuddy.data.remote.PropertySearchService
import com.example.shacklehotelbuddy.data.remote.mapper.toPropertyList
import com.example.shacklehotelbuddy.data.remote.model.ChildJson
import com.example.shacklehotelbuddy.data.remote.model.DateJson
import com.example.shacklehotelbuddy.data.remote.model.DestinationJson
import com.example.shacklehotelbuddy.data.remote.model.FiltersJson
import com.example.shacklehotelbuddy.data.remote.model.PriceJson
import com.example.shacklehotelbuddy.data.remote.model.PropertyRequestJson
import com.example.shacklehotelbuddy.data.remote.model.RoomJson
import com.example.shacklehotelbuddy.data.repository.model.PropertiesResult
import com.example.shacklehotelbuddy.search.model.SearchQuery
import java.util.Date


internal class SearchRepositoryImpl(
    private val apiService: PropertySearchService,
    private val database: ShackleDatabase
) : SearchRepository {

    override suspend fun getProperties(
        checkInDateMillis: Long,
        checkOutDateMillis: Long,
        adults: Int,
        children: Int
    ): PropertiesResult = retrofitApiCall(
        call = {
            apiService.properties(
                PropertyRequestJson(
                    currency = DEFAULT_CURRENCY,
                    eapid = DEFAULT_EAP_ID,
                    locale = DEFAULT_LOCALE,
                    siteId = DEFAULT_SITE_ID,
                    destination = DestinationJson(DEFAULT_REGION_ID),
                    checkInDate = DateJson(
                        day = Date(checkInDateMillis).getDayOfMonth(),
                        month = Date(checkInDateMillis).getMonthOfYear(),
                        year = Date(checkInDateMillis).getCurrentYear()
                    ),
                    checkOutDate = DateJson(
                        day = Date(checkOutDateMillis).getDayOfMonth(),
                        month = Date(checkOutDateMillis).getMonthOfYear(),
                        year = Date(checkOutDateMillis).getCurrentYear()
                    ),
                    rooms = listOf(
                        RoomJson(
                            adults,
                            children = createChildren(children)
                        )
                    ),
                    resultsStartingIndex = DEFAULT_RESULT_STARTING_INDEX,
                    resultsSize = DEFAULT_RESULT_SIZE,
                    filters = FiltersJson(
                        PriceJson(
                            min = DEFAULT_MIN_PRICE,
                            max = DEFAULT_MAX_PRICE
                        )
                    ),
                    sort = DEFAULT_PRICE_SORT
                )
            )
        },
        success = { response ->
            PropertiesResult.Success(response.toPropertyList())
        },
        failure = { body, message -> PropertiesResult.Failure },
        noInternet = { PropertiesResult.NoInternet }
    )

    private fun createChildren(children: Int): List<ChildJson> {
        val list = mutableListOf<ChildJson>()
        repeat(children) {
            list.add(ChildJson(DEFAULT_CHILDREN_AGE))
        }
        return list
    }

    override suspend fun getSearchQueries(): List<SearchQuery> {
        return database.withTransaction {
            val results = database.searchQueryDao().getSearchQuery().map { it.toSearchQuery() }

            if (results.size <= 3) {
                results
            } else {
                results.subList(0, 3)
            }
        }
    }

    override suspend fun insertSearchQuery(query: SearchQuery) {
        database.withTransaction {
            database.searchQueryDao().insertSearchQuery(query.toSearchQueryEntity())
        }
    }
}
package com.example.shacklehotelbuddy.data.local.mapper

import com.example.shacklehotelbuddy.data.local.SearchQueryEntity
import com.example.shacklehotelbuddy.search.model.SearchQuery

fun SearchQuery.toSearchQueryEntity() =
    SearchQueryEntity(timestamp, checkInDateMillis, checkOutDateMillis, adults, children)

fun SearchQueryEntity.toSearchQuery() =
    SearchQuery(checkInDateMillis, checkOutDateMillis, adults, children, timestamp)

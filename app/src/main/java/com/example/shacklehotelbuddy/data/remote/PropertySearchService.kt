package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.data.remote.model.PropertyDetailsRequestJson
import com.example.shacklehotelbuddy.data.remote.model.PropertyRequestJson
import com.example.shacklehotelbuddy.data.remote.model.PropertyResponseJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface PropertySearchService {

    @POST("properties/v2/list")
    suspend fun properties(
        @Body request: PropertyRequestJson
    ): Response<PropertyResponseJson>

    @POST("/detail")
    suspend fun getPropertiesDetails(
        @Body request: PropertyDetailsRequestJson
    ): String
}
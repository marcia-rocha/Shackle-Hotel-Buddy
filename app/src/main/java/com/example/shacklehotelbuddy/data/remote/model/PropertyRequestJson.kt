package com.example.shacklehotelbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class PropertyRequestJson(
    @SerializedName("currency") val currency: String,
    @SerializedName("eapid") val eapid: Int,
    @SerializedName("locale") val locale: String,
    @SerializedName("siteId") val siteId: Long,
    @SerializedName("destination") val destination: DestinationJson,
    @SerializedName("checkInDate") val checkInDate: DateJson,
    @SerializedName("checkOutDate") val checkOutDate: DateJson,
    @SerializedName("rooms") val rooms: List<RoomJson>,
    @SerializedName("resultsStartingIndex") val resultsStartingIndex: Int,
    @SerializedName("resultsSize") val resultsSize: Int,
    @SerializedName("sort") val sort: String,
    @SerializedName("filters") val filters: FiltersJson
)

data class DestinationJson(
    @SerializedName("regionId") val regionId: String
)

data class DateJson(
    @SerializedName("day") val day: Int,
    @SerializedName("month") val month: Int,
    @SerializedName("year") val year: Int
)

data class RoomJson(
    @SerializedName("adults") val adults: Int,
    @SerializedName("children") val children: List<ChildJson>
)

data class ChildJson(
    @SerializedName("age") val age: Int
)

data class FiltersJson(
    @SerializedName("price") val price: PriceJson
)

data class PriceJson(
    @SerializedName("max") val max: Double,
    @SerializedName("min") val min: Double
)
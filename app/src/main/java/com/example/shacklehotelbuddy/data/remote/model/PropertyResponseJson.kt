package com.example.shacklehotelbuddy.data.remote.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal


//not accounting for nulls, which can be dangerous
internal data class PropertyResponseJson(
    @SerializedName("data") val data: DataResponseJson
)

internal data class DataResponseJson(
    @SerializedName("propertySearch") val propertySearch: PropertySearchJson
)

internal data class PropertySearchJson(
    @SerializedName("properties") val properties: List<PropertyJson>
)

internal data class PropertyJson(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("propertyImage") val propertyImage: PropertyImageJson,
    @SerializedName("neighborhood") val neighborhood: NeighborhoodJson?,
    @SerializedName("reviews") val reviews: ReviewsJson,
    @SerializedName("price") val price: PropertyPriceJson
)

internal data class PropertyPriceJson(
    @SerializedName("lead") val lead: LeadJson
)

data class LeadJson(
    @SerializedName("amount") val amount: BigDecimal,
    @SerializedName("currencyInfo") val currencyInfo: CurrencyInfoJson,
    @SerializedName("formatted") val formatted: String
)

data class CurrencyInfoJson(
    @SerializedName("code") val code: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("formatted") val formatted: String
)

data class ReviewsJson(
    @SerializedName("score") val score: Double
)

data class PropertyImageJson(
    @SerializedName("image")
    val image: ImageJson
)

data class ImageJson(
    @SerializedName("url")
    val url: String
)

data class NeighborhoodJson(
    @SerializedName("name")
    val name: String
)
package com.example.shacklehotelbuddy.data.repository.model

sealed class PropertiesResult {
    data class Success(val properties: List<Property>) : PropertiesResult()
    object Failure : PropertiesResult()
    object NoInternet : PropertiesResult()
}
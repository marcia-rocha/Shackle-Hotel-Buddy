package com.example.shacklehotelbuddy.data.remote.mapper

import com.example.shacklehotelbuddy.data.remote.model.PropertyResponseJson
import com.example.shacklehotelbuddy.data.repository.model.Property


internal fun PropertyResponseJson.toPropertyList(): List<Property> {
    return data.propertySearch.properties.map {
        Property(
            it.id,
            it.name,
            it.propertyImage.image.url,
            it.price.lead.formatted,
            it.neighborhood?.name ?: "",
            it.reviews.score
        )
    }
}

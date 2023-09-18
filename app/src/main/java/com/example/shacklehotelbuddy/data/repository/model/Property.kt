package com.example.shacklehotelbuddy.data.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Property(
    val id: String,
    val name: String,
    val imgUrl: String,
    val price: String,
    val location: String,
    val rating: Double
) : Parcelable
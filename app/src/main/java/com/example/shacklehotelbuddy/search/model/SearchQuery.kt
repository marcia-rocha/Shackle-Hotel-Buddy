package com.example.shacklehotelbuddy.search.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SearchQuery(
    val checkInDateMillis: Long,
    val checkOutDateMillis: Long,
    val adults: Int,
    val children: Int,
    val timestamp: Long
) : Parcelable

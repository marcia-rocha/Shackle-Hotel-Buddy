package com.example.shacklehotelbuddy.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchQueryEntity(
    @PrimaryKey
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
    @ColumnInfo(name = "check_in_date_millis")
    val checkInDateMillis: Long,
    @ColumnInfo(name = "check_out_date_millis")
    val checkOutDateMillis: Long,
    @ColumnInfo(name = "adults")
    val adults: Int,
    @ColumnInfo(name = "children")
    val children: Int
)
package com.example.shacklehotelbuddy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

const val SHACKLE_DB = "shackle_db"

@Database(
    entities = [SearchQueryEntity::class],
    version = 1
)
abstract class ShackleDatabase : RoomDatabase() {

    abstract fun searchQueryDao(): SearchQueryDao
}
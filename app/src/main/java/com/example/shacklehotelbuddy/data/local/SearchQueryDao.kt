package com.example.shacklehotelbuddy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchQueryDao {

    @Query("SELECT * FROM searchqueryentity ORDER BY timestamp DESC")
    suspend fun getSearchQuery(): List<SearchQueryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchQuery(entity: SearchQueryEntity)

}
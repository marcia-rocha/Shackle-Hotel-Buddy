package com.example.shacklehotelbuddy.di

import android.content.Context
import androidx.room.Room
import com.example.shacklehotelbuddy.data.local.SHACKLE_DB
import com.example.shacklehotelbuddy.data.local.ShackleDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataBaseModule {

    val database_module = module {
        single { provideDatabase(androidContext()) }
    }

    private fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, ShackleDatabase::class.java, SHACKLE_DB)
            .fallbackToDestructiveMigration()
            .build()
}
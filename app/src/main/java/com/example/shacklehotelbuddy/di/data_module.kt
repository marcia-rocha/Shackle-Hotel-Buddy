
package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.data.repository.SearchRepository
import com.example.shacklehotelbuddy.data.repository.SearchRepositoryImpl
import org.koin.dsl.module

val data_module = module {
    factory<SearchRepository> { SearchRepositoryImpl(get(), get()) }
}
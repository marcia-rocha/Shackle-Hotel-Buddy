package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.search.ui.search.SearchViewModel
import com.example.shacklehotelbuddy.search.usecase.GetPropertiesUseCase
import com.example.shacklehotelbuddy.search.usecase.GetRecentSearchesUseCase
import com.example.shacklehotelbuddy.search.usecase.UpdateRecentSearchesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val feature_module = module {
    factory { GetPropertiesUseCase(get()) }
    factory { UpdateRecentSearchesUseCase(get()) }
    factory { GetRecentSearchesUseCase(get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
}
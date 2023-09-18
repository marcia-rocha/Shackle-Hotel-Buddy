package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.di.DataBaseModule.database_module

val modules = listOf(data_module, network_module, database_module, feature_module)

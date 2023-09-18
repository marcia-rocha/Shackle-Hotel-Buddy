package com.example.shacklehotelbuddy

import android.app.Application
import com.example.shacklehotelbuddy.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class ShackleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(this, modules)
    }

    private fun initKoin(application: Application, modules: List<Module>) {
        startKoin {
            androidContext(application)
            androidLogger(Level.ERROR)
            allowOverride(true)
        }
        loadKoinModules(modules)
    }
}
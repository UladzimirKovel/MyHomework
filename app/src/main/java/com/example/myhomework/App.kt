package com.example.myhomework

import android.app.Application
import com.example.myhomework.di.listAutoModule
import com.example.myhomework.di.sharedPrefModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        koinInit()
    }

    private fun koinInit() {

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(sharedPrefModule, listAutoModule)
        }
    }
}
package com.brhdc.binjwatch

import android.app.Application
import com.brhdc.binjwatch.di.appModule
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }

}
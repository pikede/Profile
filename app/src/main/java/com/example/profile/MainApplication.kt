package com.example.profile

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}
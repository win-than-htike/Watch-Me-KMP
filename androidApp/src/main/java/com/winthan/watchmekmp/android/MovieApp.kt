package com.winthan.watchmekmp.android

import android.app.Application
import com.winthan.watchmekmp.android.di.appModule
import com.winthan.watchmekmp.di.getSharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(appModule + getSharedModules())
        }
    }
}
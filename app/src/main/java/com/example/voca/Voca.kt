package com.example.voca

import android.app.Application
import com.example.voca.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Voca: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Voca)
            androidLogger()
            modules(appModule)
        }
    }

}
package com.example.dicodingjetpackpro

import android.app.Application
import com.example.dicodingjetpackpro.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule,
                adapterModule
            )
        }

    }
}
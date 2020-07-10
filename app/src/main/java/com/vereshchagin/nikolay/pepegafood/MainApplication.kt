package com.vereshchagin.nikolay.pepegafood

import android.app.Application

/**
 * Singleton класс приложения.
 */
class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
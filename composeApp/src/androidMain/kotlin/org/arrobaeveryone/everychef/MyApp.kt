package org.arrobaeveryone.everychef

import android.app.Application
import di.KoinInitializer

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(context = applicationContext).init()
    }
}
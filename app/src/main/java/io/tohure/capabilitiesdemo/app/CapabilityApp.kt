package io.tohure.capabilitiesdemo.app

import android.app.Application
import io.tohure.capabilitiesdemo.di.initDI

class CapabilityApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}
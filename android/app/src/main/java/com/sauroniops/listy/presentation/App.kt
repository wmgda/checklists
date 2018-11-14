package com.sauroniops.listy.presentation

import android.app.Application
import com.sauroniops.listy.BuildConfig
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())


            Timber.tag("kitek").d("Hello world! ")
        }
    }
}

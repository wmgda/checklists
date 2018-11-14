package com.sauroniops.listy.presentation

import android.app.Application
import com.sauroniops.listy.BuildConfig
import com.sauroniops.listy.data.di.module.androidModule
import com.sauroniops.listy.data.di.module.repositoryModule
import com.sauroniops.listy.data.di.module.serviceModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber

class App : Application(), KodeinAware {

    override val kodein = Kodein {
        import(androidModule(this@App))
        import(serviceModule)
        import(repositoryModule)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

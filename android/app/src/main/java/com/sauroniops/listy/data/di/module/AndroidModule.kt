package com.sauroniops.listy.data.di.module


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.sauroniops.listy.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun androidModule(app: Application) = Kodein.Module(name = "AndroidModule") {

    bind<Context>(tag = APP_CONTEXT) with instance(app)

    bind<SharedPreferences>() with singleton {
        val name = "${BuildConfig.APPLICATION_ID}.general"
        app.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

}

const val APP_CONTEXT = "ApplicationContext"

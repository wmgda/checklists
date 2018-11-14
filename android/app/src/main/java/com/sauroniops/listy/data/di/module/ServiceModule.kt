package com.sauroniops.listy.data.di.module

import com.algolia.search.saas.Client
import com.sauroniops.listy.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val serviceModule = Kodein.Module(name = "AppModule") {

    bind<Client>() with singleton {
        Client(BuildConfig.ALGOLIA_APP_ID, BuildConfig.ALGOLIA_APP_KEY)
    }
}

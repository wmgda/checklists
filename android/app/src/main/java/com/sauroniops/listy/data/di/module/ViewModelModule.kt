package com.sauroniops.listy.data.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sauroniops.listy.data.di.ViewModelFactory
import com.sauroniops.listy.presentation.main.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val viewModelModule = Kodein.Module(name = "ViewModelModule") {

    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }

    bindViewModel<MainViewModel>() with provider { MainViewModel(instance()) }
}

private inline fun <reified T : ViewModel> Kodein.Builder.bindViewModel(
    overrides: Boolean? = null
): Kodein.Builder.TypeBinder<T> = bind<T>(T::class.java.name, overrides)

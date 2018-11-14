package com.sauroniops.listy.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DKodein
import org.kodein.di.generic.instance

class ViewModelFactory(private val injector: DKodein) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return injector.instance<ViewModel>(tag = modelClass.name) as T
    }
}

package com.sauroniops.listy.presentation.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val subscriptions = CompositeDisposable()
    
    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}

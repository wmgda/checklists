package com.sauroniops.listy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.repository.ChecklistRepository
import com.sauroniops.listy.presentation.addTo
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val repo: ChecklistRepository
) : ViewModel() {

    val results: MutableLiveData<List<Checklist>> by lazy {
        MutableLiveData<List<Checklist>>().apply { search("") }
    }
    val error = MutableLiveData<Throwable?>()

    private val subscriptions = CompositeDisposable()

    fun search(query: String) {
        subscriptions.clear()
        repo.search(query).subscribe({ items ->
            this.results.value = items
            this.error.value = null
        }, { err ->
            this.error.value = err
        }).addTo(subscriptions)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}

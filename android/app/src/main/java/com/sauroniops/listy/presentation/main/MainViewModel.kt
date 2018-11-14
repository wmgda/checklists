package com.sauroniops.listy.presentation.main

import androidx.lifecycle.MutableLiveData
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.repository.ChecklistRepository
import com.sauroniops.listy.presentation.common.BaseViewModel
import com.sauroniops.listy.presentation.common.addTo

class MainViewModel(
    private val repo: ChecklistRepository
) : BaseViewModel() {

    val results: MutableLiveData<List<Checklist>> by lazy {
        MutableLiveData<List<Checklist>>().apply { search("") }
    }
    val error = MutableLiveData<Throwable?>()
    val isLoading = MutableLiveData<Boolean>()
    private var lastQuery: String? = null

    fun search(query: String) {
        if (this.lastQuery == query) {
            this.isLoading.value = false
            return
        }

        subscriptions.clear()
        this.isLoading.value = true
        repo.search(query).subscribe({ items ->
            this.lastQuery = query
            this.results.value = items
            this.error.value = null
            this.isLoading.value = false
        }, { err ->
            this.error.value = err
            this.isLoading.value = false
        }).addTo(subscriptions)
    }
}

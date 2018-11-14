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


    fun search(query: String) {
        subscriptions.clear()
        repo.search(query).subscribe({ items ->
            this.results.value = items
            this.error.value = null
        }, { err ->
            this.error.value = err
        }).addTo(subscriptions)
    }
}

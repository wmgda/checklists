package com.sauroniops.listy.presentation.item

import androidx.lifecycle.MutableLiveData
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.repository.ChecklistRepository
import com.sauroniops.listy.presentation.common.BaseViewModel
import com.sauroniops.listy.presentation.common.addTo

class ItemViewModel(
    private val repo: ChecklistRepository
) : BaseViewModel() {

    private var id: String = ""

    val title = MutableLiveData<String>()
    val model = MutableLiveData<Checklist?>()
    val error = MutableLiveData<Throwable?>()

    fun init(id: String, title: String = "") {
        this.id = id
        this.title.value = title

        fetch()
    }

    fun toggleCheckbox(checkboxId: String, isChecked: Boolean) {
        repo.updateChecked(id, checkboxId, isChecked).subscribe({ item ->
            this.model.value = item
            this.error.value = null
        }, { err ->
            this.error.value = err
        }).addTo(subscriptions)
    }

    private fun fetch() {
        repo.get(id).subscribe({ item ->
            this.model.value = item
            this.error.value = null
        }, { err ->
            this.error.value = err
        }).addTo(subscriptions)
    }
}

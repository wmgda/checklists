package com.sauroniops.listy.data.repository

import com.sauroniops.listy.data.model.Checklist
import io.reactivex.Single

interface ChecklistRepository {

    fun search(query: String): Single<List<Checklist>>

}

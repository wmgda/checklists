package com.sauroniops.listy.data.repository

import com.algolia.search.saas.Client
import com.sauroniops.listy.data.model.Checklist
import io.reactivex.Single

class ChecklistRepositoryImpl(
    private val client: Client
) : ChecklistRepository {

    override fun search(query: String): Single<List<Checklist>> {
        return Single.just(emptyList())
    }
}

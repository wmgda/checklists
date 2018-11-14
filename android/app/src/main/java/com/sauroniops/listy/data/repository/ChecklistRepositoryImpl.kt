package com.sauroniops.listy.data.repository

import com.algolia.search.saas.Client
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.ChecklistItem
import io.reactivex.Single

class ChecklistRepositoryImpl(
    private val client: Client
) : ChecklistRepository {

    override fun search(query: String): Single<List<Checklist>> {
        // TODO Mock data
        return Single.just(
            listOf(
                Checklist(
                    "one",
                    "This is first item",
                    listOf(
                        ChecklistItem("id-1", "Todo 1", false),
                        ChecklistItem("id-2", "Todo 2", false),
                        ChecklistItem("id-3", "Todo 3", false)
                    )
                ),
                Checklist(
                    "two",
                    "This is second item",
                    listOf(
                        ChecklistItem("id-1", "Todo 1", false),
                        ChecklistItem("id-2", "Todo 2", false),
                        ChecklistItem("id-3", "Todo 3", false)
                    )
                ),
                Checklist(
                    "three",
                    "This is third item",
                    listOf(
                        ChecklistItem("id-1", "Todo 1", false),
                        ChecklistItem("id-2", "Todo 2", false),
                        ChecklistItem("id-3", "Todo 3", false)
                    )
                ),
                Checklist(
                    "four",
                    "This is fourth item",
                    listOf(
                        ChecklistItem("id-1", "Todo 1", false),
                        ChecklistItem("id-2", "Todo 2", false),
                        ChecklistItem("id-3", "Todo 3", false)
                    )
                )

            )
        )
    }
}

package com.sauroniops.listy.data.repository

import com.algolia.search.saas.Client
import com.algolia.search.saas.CompletionHandler
import com.algolia.search.saas.Query
import com.google.gson.Gson
import com.sauroniops.listy.BuildConfig
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.SearchResponse
import io.reactivex.Single

class ChecklistRepositoryImpl(
    private val client: Client
) : ChecklistRepository {

    private val gson = Gson()

    override fun search(query: String): Single<List<Checklist>> {
        return Single.create { s ->
            val index = client.getIndex(BuildConfig.ALGOLIA_SEARCH_INDEX)
            val completionHandler = CompletionHandler { content, error ->
                if (null != error) {
                    if (!s.isDisposed) s.onError(error)
                } else {
                    try {
                        val searchResponse = gson.fromJson(content.toString(), SearchResponse::class.java)
                        if (!s.isDisposed) s.onSuccess(searchResponse.getItems())
                    } catch (e: Exception) {
                        if (!s.isDisposed) s.onError(e)
                    }
                }
            }
            index.searchAsync(Query(query), completionHandler)
        }
    }
}

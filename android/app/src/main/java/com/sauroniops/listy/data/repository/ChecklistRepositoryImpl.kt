package com.sauroniops.listy.data.repository

import com.algolia.search.saas.Client
import com.algolia.search.saas.CompletionHandler
import com.algolia.search.saas.Query
import com.google.gson.Gson
import com.sauroniops.listy.BuildConfig
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.SearchResponse
import io.reactivex.Single
import timber.log.Timber

class ChecklistRepositoryImpl(
    private val client: Client
) : ChecklistRepository {

    private val gson = Gson()

    override fun get(id: String): Single<Checklist> {
        return Single.create { s ->
            val index = client.getIndex(BuildConfig.ALGOLIA_SEARCH_INDEX)

            val completionHandler = CompletionHandler { content, error ->
                if (null != error) {
                    if (!s.isDisposed) s.onError(error)
                } else {
                    Timber.e("FunName:get *****$content *****")
                    try {
                        val searchResponse = gson.fromJson(content.toString(), SearchResponse::class.java)
                        val checklist = searchResponse.hits.first()
                        Timber.e("FunName:get *****$checklist *****")
                        if (!s.isDisposed) s.onSuccess(checklist)
                    } catch (e: Exception) {
                        if (!s.isDisposed) s.onError(e)
                    }
                }
            }
            index.searchAsync(Query(id).setFacets("id"), completionHandler)
        }
    }

    override fun search(query: String): Single<List<Checklist>> {
        Timber.tag("kitek").d("ChecklistRepository.search=$query")

        return Single.create { s ->
            val index = client.getIndex(BuildConfig.ALGOLIA_SEARCH_INDEX)
            val completionHandler = CompletionHandler { content, error ->
                if (null != error) {
                    if (!s.isDisposed) s.onError(error)
                } else {
                    try {
                        val searchResponse = gson.fromJson(content.toString(), SearchResponse::class.java)
                        if (!s.isDisposed) s.onSuccess(searchResponse.hits)
                    } catch (e: Exception) {
                        if (!s.isDisposed) s.onError(e)
                    }
                }
            }
            index.searchAsync(Query(query), completionHandler)
        }
    }
}

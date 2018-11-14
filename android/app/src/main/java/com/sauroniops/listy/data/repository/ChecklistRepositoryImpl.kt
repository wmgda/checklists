package com.sauroniops.listy.data.repository

import android.content.SharedPreferences
import com.algolia.search.saas.Client
import com.algolia.search.saas.CompletionHandler
import com.algolia.search.saas.Query
import com.google.gson.Gson
import com.sauroniops.listy.BuildConfig
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.SearchResponse
import io.reactivex.Single

class ChecklistRepositoryImpl(
    private val client: Client,
    private val local: SharedPreferences
) : ChecklistRepository {

    private val gson = Gson()

    override fun get(id: String): Single<Checklist> {
        return Single.create { s ->
            val index = client.getIndex(BuildConfig.ALGOLIA_SEARCH_INDEX)

            val completionHandler = CompletionHandler { content, error ->
                if (null != error) {
                    if (!s.isDisposed) s.onError(error)
                } else {
                    try {
                        val searchResponse = gson.fromJson(content.toString(), SearchResponse::class.java)
                        val checklist = searchResponse.checklist

                        if (!s.isDisposed) {
                            if (null == checklist) s.onError(Exception("Checklist not found"))
                            else s.onSuccess(updateWithLocalValues(checklist))
                        }

                    } catch (e: Exception) {
                        if (!s.isDisposed) s.onError(e)
                    }
                }
            }
            
            index.searchAsync(Query(id).setFacets("id"), completionHandler)
        }
    }

    private fun updateWithLocalValues(checklist: Checklist): Checklist {
        val checkedValues = getChecked(checklist.id)
        if (checkedValues.isEmpty()) return checklist

        val updatedItems = checklist.items.map { it.copy(isChecked = checkedValues.contains(it.id)) }
        return checklist.copy(items = updatedItems)
    }

    override fun search(query: String): Single<List<Checklist>> {
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

    override fun updateChecked(idList: String, idCheckbox: String, isChecked: Boolean): Single<Checklist> {
        return Single.defer {
            val checkedItems = ArrayList(getChecked(idList))
            if (isChecked) checkedItems.add(idCheckbox) else checkedItems.remove(idCheckbox)
            setChecked(idList, checkedItems)

            get(idList)
        }
    }

    private fun getChecked(idList: String): List<String> {
        val localValue = local.getString(idList, "") ?: return emptyList()
        if (localValue.isEmpty()) return emptyList()
        return localValue.split(FORMAT_SEP).filter { it.isNotEmpty() }
    }

    private fun setChecked(idList: String, checkedIds: List<String>) {
        local.edit().putString(idList, checkedIds.joinToString(FORMAT_SEP)).apply()
    }

    companion object {
        private const val FORMAT_SEP = "|"
    }
}

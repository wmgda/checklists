package com.sauroniops.listy.data.model

data class SearchResponse(val hits: List<SearchResults>) {

    fun getItems(): List<Checklist> = hits.firstOrNull()?.items ?: emptyList()

}

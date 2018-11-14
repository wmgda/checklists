package com.sauroniops.listy.data.model

data class SearchResponse(val hits: List<Checklist>) {

    val checklist: Checklist?
        get() {
            val item = hits.firstOrNull() ?: return null

            // ensure that items contains id
            val itemsWithId = item.items.mapIndexed { index, it ->
                if (null == it.id) it.copy(id = "${item.id}-$index") else it
            }
            
            return item.copy(items = itemsWithId)
        }
}

package com.sauroniops.listy.data.model

data class Checklist(
    val id: String,
    val title: String,
    val items: List<ChecklistItem>
)
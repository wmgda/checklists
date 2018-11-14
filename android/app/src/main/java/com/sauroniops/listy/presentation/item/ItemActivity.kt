package com.sauroniops.listy.presentation.item

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.ChecklistItem
import com.sauroniops.listy.presentation.addTo
import com.sauroniops.listy.presentation.item.adapter.ItemListAdapter
import com.sauroniops.listy.presentation.main.adapter.MainListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import timber.log.Timber

class ItemActivity : AppCompatActivity(), ItemListAdapter.OnItemClickListener {
    private lateinit var adapter: ItemListAdapter
    override fun onItemClick(item: ChecklistItem) {
        Timber.e("FunName:onItemClick *****${item.title} *****")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ItemListAdapter(
            Checklist(
                "1", "title", listOf(
                    ChecklistItem("1", "itemTitle1", false),
                    ChecklistItem("2", "itemTitle2", false),
                    ChecklistItem("3", "itemTitle3", false),
                    ChecklistItem("4", "itemTitle4", false)
                )
            ), this
        )

        toolbar.searchEditText.visibility = View.GONE
        toolbar.title = "Title"

        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

    }
}

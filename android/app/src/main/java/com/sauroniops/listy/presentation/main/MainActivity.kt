package com.sauroniops.listy.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.presentation.main.adapter.MainListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() ,MainListAdapter.OnItemClickListener{
    override fun onItemClick(item: Checklist) {
        Timber.e("FunName:onItemClick *****${item.title} *****")
    }

    private lateinit var adapter: MainListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainListAdapter(
            listOf(
                Checklist("1", "List1",listOf()),
                Checklist("2", "List2",listOf()),
                Checklist("3", "List3",listOf()),
                Checklist("4", "List4",listOf()),
                Checklist("5", "List5",listOf())
            ),this
        )
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }
}

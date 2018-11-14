package com.sauroniops.listy.presentation.main

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.presentation.item.ItemActivity
import com.sauroniops.listy.presentation.main.adapter.MainListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware, MainListAdapter.OnItemClickListener {

    override val kodein: Kodein by closestKodein()
    private val viewModeFactory: ViewModelProvider.Factory by instance()
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModeFactory).get(MainViewModel::class.java)
    }

    private lateinit var adapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainListAdapter(emptyList(), this)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL))

        registerObservers()
    }

    private fun registerObservers() {
        viewModel.results.observe(this, Observer { adapter.fillAdapter(it) })
    }

    override fun onItemClick(item: Checklist) {
        val intent = Intent(this, ItemActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, item.id)
        }
        startActivity(intent)
    }
}

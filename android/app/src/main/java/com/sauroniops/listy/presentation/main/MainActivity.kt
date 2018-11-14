package com.sauroniops.listy.presentation.main

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.Editable
import android.text.TextWatcher
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
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware, MainListAdapter.OnItemClickListener, TextWatcher {

    override val kodein: Kodein by closestKodein()
    private val viewModeFactory: ViewModelProvider.Factory by instance()
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModeFactory).get(MainViewModel::class.java)
    }

    private val adapter = MainListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        registerObservers()
    }

    private fun setupView() {
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        val itemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)
        toolbar.searchEditText.addTextChangedListener(this)
    }

    private fun registerObservers() {
        viewModel.results.observe(this, Observer { adapter.items = it })
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val query = text?.toString() ?: ""
        viewModel.search(query)
    }

    override fun onItemClick(item: Checklist) {
        val intent = Intent(this, ItemActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, item.id)
        }
        startActivity(intent)
    }
}

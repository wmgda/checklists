package com.sauroniops.listy.presentation.main

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.repository.ChecklistRepository
import com.sauroniops.listy.presentation.addTo
import com.sauroniops.listy.presentation.item.ItemActivity
import com.sauroniops.listy.presentation.main.adapter.MainListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class MainActivity : AppCompatActivity(), KodeinAware, MainListAdapter.OnItemClickListener {

    override val kodein: Kodein by closestKodein()
    private val checklistRepository: ChecklistRepository by instance()
    private val subscriptions = CompositeDisposable()
    private lateinit var adapter: MainListAdapter

    override fun onItemClick(item: Checklist) {
        val intent = Intent(this, ItemActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, item.title)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainListAdapter(emptyList(),this)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


        checklistRepository.search("query").subscribe({ items ->
            adapter.fillAdapter(items)
        }, { err ->
            Timber.tag("kitek").d("Error during fetching: $err ")
        }).addTo(subscriptions)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}

package com.sauroniops.listy.presentation.item

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.ChecklistItem
import com.sauroniops.listy.data.repository.ChecklistRepository
import com.sauroniops.listy.presentation.addTo
import org.kodein.di.generic.instance
import com.sauroniops.listy.presentation.item.adapter.ItemListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import timber.log.Timber

class ItemActivity : AppCompatActivity(), KodeinAware, ItemListAdapter.OnItemClickListener {

    private lateinit var adapter: ItemListAdapter
    override val kodein: Kodein by closestKodein()
    private val checklistRepository: ChecklistRepository by instance()
    private val subscriptions = CompositeDisposable()

    override fun onItemClick(item: ChecklistItem) {
        Timber.e("FunName:onItemClick *****${item.title} *****")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra(EXTRA_MESSAGE)) {
            val id = intent.getStringExtra(EXTRA_MESSAGE)
            checklistRepository.get(id).subscribe({ item ->
                toolbar.title = item.title
                adapter = ItemListAdapter(item, this)
            }, { err ->
                Timber.tag("kitek").d("Error during fetching: $err ")
            }).addTo(subscriptions)
        }
        toolbar.searchEditText.visibility = View.GONE

        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}

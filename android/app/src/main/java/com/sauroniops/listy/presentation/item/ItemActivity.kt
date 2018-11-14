package com.sauroniops.listy.presentation.item

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.ChecklistItem
import com.sauroniops.listy.presentation.item.adapter.ItemListAdapter
import kotlinx.android.synthetic.main.item_activity.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class ItemActivity : AppCompatActivity(), KodeinAware, ItemListAdapter.OnItemClickListener {

    private val adapter = ItemListAdapter(this)
    override val kodein: Kodein by closestKodein()
    private val viewModeFactory: ViewModelProvider.Factory by instance()
    private val viewModel: ItemViewModel by lazy {
        ViewModelProviders.of(this, viewModeFactory).get(ItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)

        val extras = intent.extras ?: Bundle()
        val id = extras.getString(ID, "")
        val title = extras.getString(TITLE, "")

        viewModel.init(id, title)

        setupView()
        registerObservers()
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun registerObservers() {
        viewModel.title.observe(this, Observer { title -> setTitle(title) })
        viewModel.model.observe(this, Observer { model -> this.adapter.item = model })
    }

    override fun onItemClick(item: ChecklistItem) {
        Timber.e("FunName:onItemClick *****${item.title} *****")
    }

    companion object {

        fun createBundle(item: Checklist): Bundle {
            val bundle = Bundle()
            bundle.putString(ID, item.id)
            bundle.putString(TITLE, item.title)

            return bundle
        }

        private const val ID = "id"
        private const val TITLE = "title"
    }
}

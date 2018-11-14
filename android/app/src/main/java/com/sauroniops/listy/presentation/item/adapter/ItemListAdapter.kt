package com.sauroniops.listy.presentation.item.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.ChecklistItem
import kotlinx.android.synthetic.main.checklist_item_details.view.*

class ItemListAdapter(
    private var checklist: Checklist, private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: ChecklistItem)
    }

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindListener(item: ChecklistItem, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemListAdapter.ItemViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.checklist_item_details, parent, false)
        return ItemListAdapter.ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemListAdapter.ItemViewHolder, position: Int) {
        holder.view.checkbox.text = checklist.items[position].title
        holder.bindListener(checklist.items[position], listener)
    }

    override fun getItemCount() = checklist.items.size
}
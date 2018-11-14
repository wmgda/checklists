package com.sauroniops.listy.presentation.item.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.ChecklistItem
import com.sauroniops.listy.presentation.common.inflate
import kotlinx.android.synthetic.main.checklist_item_details.view.*

class ItemListAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    var item: Checklist? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemListAdapter.ItemViewHolder {
        val layout = parent.inflate(R.layout.checklist_item_details, false)
        return ItemListAdapter.ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemListAdapter.ItemViewHolder, position: Int) {
        val item = this.item?.items?.getOrNull(position) ?: return

        holder.view.checkbox.text = item.title
        holder.bindListener(item, listener)
    }

    override fun getItemCount() = item?.items?.size ?: 0

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindListener(item: ChecklistItem, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: ChecklistItem)
    }
}

package com.sauroniops.listy.presentation.item.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.data.model.ChecklistItem
import com.sauroniops.listy.presentation.common.inflate

class ItemListAdapter(
    private val listener: OnItemChangedListener
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>(), CompoundButton.OnCheckedChangeListener {

    var item: Checklist? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemListAdapter.ItemViewHolder {
        val layout = parent.inflate(R.layout.checklist_item_details, false)
        val checkbox = layout.findViewById<CheckBox>(R.id.checkbox)
        checkbox.setOnCheckedChangeListener(this)

        return ItemListAdapter.ItemViewHolder(checkbox, layout)
    }

    override fun onBindViewHolder(holder: ItemListAdapter.ItemViewHolder, position: Int) {
        val item = this.item?.items?.getOrNull(position) ?: return

        holder.bind(item, position)
    }

    override fun getItemCount() = item?.items?.size ?: 0

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val position: Int = buttonView?.tag as? Int? ?: return
        val item = this.item?.items?.getOrNull(position) ?: return

        listener.onItemChange(item, isChecked)
    }

    class ItemViewHolder(
        private val checkBox: CheckBox, view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: ChecklistItem, position: Int) {
            checkBox.text = item.title
            checkBox.tag = position
        }
    }

    interface OnItemChangedListener {
        fun onItemChange(item: ChecklistItem, isChecked: Boolean)
    }
}

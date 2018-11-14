package com.sauroniops.listy.presentation.main.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import com.sauroniops.listy.presentation.inflate

class MainListAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MainListAdapter.ChecklistViewHolder>(), View.OnClickListener {

    var items: List<Checklist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewHolder {
        val layout = parent.inflate(R.layout.checklist_item, false).apply { setOnClickListener(this@MainListAdapter) }
        val titleTxt = layout.findViewById<TextView>(R.id.titleTxt)

        return ChecklistViewHolder(titleTxt, layout)
    }

    override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
        val item = items.getOrNull(position) ?: return
        holder.bind(item, position)
    }

    override fun getItemCount() = items.size

    override fun onClick(v: View?) {
        val clickedPosition: Int = v?.tag as? Int? ?: -1
        items.getOrNull(clickedPosition)?.let { item -> listener.onItemClick(item) }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Checklist)
    }

    class ChecklistViewHolder(val titleTxt: TextView, view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Checklist, position: Int) {
            this.titleTxt.text = item.title
            itemView.tag = position
        }
    }
}

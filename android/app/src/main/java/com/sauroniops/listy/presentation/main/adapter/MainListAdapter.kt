package com.sauroniops.listy.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sauroniops.listy.R
import com.sauroniops.listy.data.model.Checklist
import kotlinx.android.synthetic.main.checklist_item.view.*

class MainListAdapter(
    private var checklists: List<Checklist>, private val listener: OnItemClickListener
) : RecyclerView.Adapter<MainListAdapter.ChecklistViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Checklist)
    }

    class ChecklistViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindListener(item: Checklist, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChecklistViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.checklist_item, parent, false)
        return ChecklistViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
        holder.view.title.text = checklists[position].title
        holder.bindListener(checklists[position], listener)
    }

    override fun getItemCount() = checklists.size

    fun fillAdapter(checklists: List<Checklist>) {
        this.checklists = checklists
        notifyDataSetChanged()
    }
}
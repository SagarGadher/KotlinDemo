package com.ashawooden.kotlindemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_basic_item.view.*

class BasicAdapter(val mContext: Context, val mBasicItemList: ArrayList<String>, val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_basic_item, parent, false) as View)

    override fun getItemCount() = mBasicItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(mBasicItemList[position], position, listener)
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: String, position: Int, listener: (Int) -> Unit) = with(itemView) {
        val tvListItem = itemView.tvListItem as TextView
        tvListItem.text = item
        itemView.setOnClickListener {
            listener(position)
        }
    }
}
package com.ashawooden.kotlindemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class GenericAdapter<T>(var listItems: List<T> = emptyList()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(listItems: List<T>) {
        this.listItems = listItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false), viewType)

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItems[position])
        holder.itemView.setOnClickListener { onClick(position) }
    }


    override fun getItemViewType(position: Int): Int = getLayoutId(position, listItems[position])


    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    abstract fun onClick(position: Int)

    internal interface Binder<T> {
        fun bind(data: T)
    }
}
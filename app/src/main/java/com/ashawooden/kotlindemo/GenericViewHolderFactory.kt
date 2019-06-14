package com.ashawooden.kotlindemo

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

object GenericViewHolderFactory {
    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.list_basic_item -> ViewHolder(view)
            R.layout.list_user_item -> UserViewHolder(view)
            else -> ViewHolder(view)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<Shlok> {
        var tvListItem: TextView
        var tvListItemCount: TextView

        init {
            tvListItemCount = itemView.findViewById(R.id.tvListItemCount)
            tvListItem = itemView.findViewById(R.id.tvListItem)
        }

        override fun bind(data: Shlok) {
            tvListItem.text = data.shlok
            tvListItemCount.text = data.index.toString()
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<User> {
        var ivImage: ImageView
        var tvUserName: TextView

        init {
            tvUserName = itemView.findViewById(R.id.tvUserName)
            ivImage = itemView.findViewById(R.id.ivUser)
        }

        override fun bind(data: User) {
            tvUserName.text = data.name
            ivImage.setImageResource(data.ivImage)
        }
    }
}
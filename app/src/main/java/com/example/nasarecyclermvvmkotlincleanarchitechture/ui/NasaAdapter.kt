package com.example.nasarecyclermvvmkotlincleanarchitechture.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasarecyclermvvmkotlincleanarchitechture.R
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.nasa.Item


class NasaAdapter() : PagingDataAdapter<Item, NasaAdapter.ViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_link, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)

        } else {
            Log.d("Recycler", "Item at position $position is null.")
        }

        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageNasa: ImageView = view.findViewById(R.id.iv_nasa)

        fun bind(link: Item) {
            val imageUrl = link.links.firstOrNull()?.href

            Glide.with(imageNasa.context)
                .load(imageUrl)
                .override(300,300)
                .placeholder(R.drawable.nasa)
                .into(imageNasa)

        }
    }


    class DiffUtilCallBack: DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.href == newItem.href
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}



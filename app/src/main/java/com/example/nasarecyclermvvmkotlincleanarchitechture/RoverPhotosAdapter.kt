package com.example.nasarecyclermvvmkotlincleanarchitechture


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.Photo
import java.util.concurrent.TimeUnit

class RoverPhotosAdapter() :
    PagingDataAdapter<Photo,RoverPhotosAdapter.ViewHolder>(DiffUtilCallBack()) {

    private var photos = listOf<Photo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rover_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
       /* holder.bind(photos[position])*/
    }

 /*   override fun getItemCount(): Int {
        return photos.size
    }*/

/*    fun updatePhotos(newPhotos: List<Photo>) {
        photos = newPhotos
        notifyDataSetChanged()

       *//* fun submitList(launches1: List<Launch>) {
            launches.clear()
            launches.addAll(launches1)
            notifyDataSetChanged()
        }*//*
    }*/

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)

        fun bind(photo: Photo) {
            Glide.with(ivPhoto.context)
                .load(photo.img_src)
                .override(200, 200)
                .into(ivPhoto)
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return  oldItem.id == newItem.id

        }
    }
}
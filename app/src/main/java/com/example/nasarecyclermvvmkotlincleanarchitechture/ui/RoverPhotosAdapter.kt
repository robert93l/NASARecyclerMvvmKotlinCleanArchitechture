package com.example.nasarecyclermvvmkotlincleanarchitechture.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasarecyclermvvmkotlincleanarchitechture.R
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.Photo
import org.w3c.dom.Text


class RoverPhotosAdapter() :
    PagingDataAdapter<Photo, RoverPhotosAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rover_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        private val tvcamera: TextView = itemView.findViewById(R.id.camera_name)
        private val tverath:  TextView = itemView.findViewById(R.id.earth_date)
        private val tvrover:  TextView = itemView.findViewById(R.id.rover_name)
        private val tvlanding:  TextView = itemView.findViewById(R.id.landing_date)
        private val tvlaunch:  TextView = itemView.findViewById(R.id.launch_date)
        private val tvstatus:  TextView = itemView.findViewById(R.id.status)

        fun bind(photo: Photo) {
            Glide.with(ivPhoto.context)
                .load(photo.img_src)
                .override(300, 300)
                .into(ivPhoto)

            tvcamera.text = "Camera: "+photo.camera.name
            tverath.text = "Earth date: " + photo.earth_date
            tvrover.text = "Name Rover: "+photo.rover.name
            tvlanding.text = "Landing "+photo.rover.landing_date
            tvlaunch.text = "Launch "+photo.rover.launch_date
            tvstatus.text = "Status: "+photo.rover.status
        }

    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return  oldItem.id == newItem.id
                    && oldItem.rover.id == oldItem.rover.id

        }
    }
}
package com.example.nasarecyclermvvmkotlincleanarchitechture.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.Photo
import com.example.nasarecyclermvvmkotlincleanarchitechture.databinding.RoverItemLayoutBinding



class RoverPhotosAdapter() :
    PagingDataAdapter<Photo, RoverPhotosAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RoverItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class ViewHolder(private val binding: RoverItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            Glide.with(binding.ivPhoto.context)
                .load(photo.img_src)
                .override(300, 300)
                .into(binding.ivPhoto)

            binding.cameraName.text = "Camera: "+photo.camera.name
            binding.earthDate.text = "Earth date: " + photo.earth_date
            binding.roverName.text = "Name Rover: "+photo.rover.name
            binding.landingDate.text = "Landing: "+photo.rover.landing_date
            binding.launchDate.text =  "Launch: "+ photo.rover.launch_date
            binding.status.text = "Status: "+photo.rover.status.uppercase()
            binding.urkLink.text = "URL: " + photo.img_src
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
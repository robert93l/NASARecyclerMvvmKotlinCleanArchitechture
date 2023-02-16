package com.example.nasarecyclermvvmkotlincleanarchitechture.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.Photo
import com.example.nasarecyclermvvmkotlincleanarchitechture.databinding.RoverItemLayoutBinding


class RoverPhotosAdapter() :
    PagingDataAdapter<Photo, RoverPhotosAdapter.ViewHolder>(DiffUtilCallBack()) {

    private var expandedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RoverItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, position == expandedPosition)
        }
    }

    inner class ViewHolder(private val binding: RoverItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var isExpanded = false

        init {
            // Set click listener for the item to toggle the expansion state
            binding.root.setOnClickListener {
                isExpanded = !isExpanded
                notifyItemChanged(bindingAdapterPosition)

                // Collapse the previously expanded item, if any
                if (expandedPosition != RecyclerView.NO_POSITION && expandedPosition != bindingAdapterPosition) {
                    notifyItemChanged(expandedPosition)
                    isExpanded = false
                }

                // Update the position of the currently expanded item
                expandedPosition = if (isExpanded) bindingAdapterPosition else RecyclerView.NO_POSITION
            }
        }

        fun bind(photo: Photo, isExpanded: Boolean) {
            this.isExpanded = isExpanded

            Glide.with(binding.ivPhoto.context)
                .load(photo.img_src)
                .override(300, 300)
                .into(binding.ivPhoto)

            // Set the maximum number of lines to show for each text field
            val maxLines = if (isExpanded) Int.MAX_VALUE else 3

            binding.cameraName.text = "Camera: " + photo.camera.name
            binding.cameraName.maxLines = maxLines

            binding.earthDate.text = "Earth date: " + photo.earth_date
            binding.earthDate.maxLines = maxLines

            binding.roverName.text = "Name Rover: " + photo.rover.name
            binding.roverName.maxLines = maxLines

            binding.landingDate.text = "Landing: " + photo.rover.landing_date
            binding.landingDate.maxLines = maxLines

            binding.launchDate.text = "Launch: " + photo.rover.launch_date
            binding.launchDate.maxLines = maxLines

            binding.status.text = "Status: " + photo.rover.status.uppercase()
            binding.status.maxLines = maxLines

            binding.urkLink.text = "URL: " + photo.img_src
            binding.urkLink.maxLines = maxLines

            // Set visibility of expand/collapse button based on current state
            binding.earthDate.visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.roverName.visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.landingDate.visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.launchDate.visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.urkLink.visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.status.visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.ivPhoto.visibility = if (isExpanded) View.VISIBLE else View.VISIBLE

        }
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

package com.example.nasarecyclermvvmkotlincleanarchitechture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.nasarecyclermvvmkotlincleanarchitechture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: RoverViewModel by viewModels()
    private lateinit var adaptermars: RoverPhotosAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()
        setUpViewModel()
    }

    private fun setUpRv() {

        adaptermars = RoverPhotosAdapter()
        binding.recyclerviewMars.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        binding.recyclerviewMars.adapter = adaptermars

    }

    private fun setUpViewModel() {

        viewModel.getPhotos(1000,1,Constants.API_KEY)
        viewModel.photos.observe(this, Observer { photoslist ->
            adaptermars.updatePhotos(photoslist)

        })
    }
}
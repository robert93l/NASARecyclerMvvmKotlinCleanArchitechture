package com.example.nasarecyclermvvmkotlincleanarchitechture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nasarecyclermvvmkotlincleanarchitechture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        refreshswipe()
    }

    private fun refreshswipe() {
        binding.swiperefresh.setOnRefreshListener {

            setUpRv()
            setUpViewModel()

            binding.swiperefresh.isRefreshing = false
        }
    }

    private fun setUpRv() {

        adaptermars = RoverPhotosAdapter()
        binding.recyclerviewMars.apply {
        adapter =adaptermars
        layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }

    }

    private fun setUpViewModel() {

            lifecycleScope.launch {
                viewModel.marsphotos.collect {

                    Log.d("aaa", "load: $it")
                    adaptermars.submitData(it)
                }
            }
        }
    }

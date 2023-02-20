package com.example.nasarecyclermvvmkotlincleanarchitechture.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nasarecyclermvvmkotlincleanarchitechture.R
import com.example.nasarecyclermvvmkotlincleanarchitechture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: RoverViewModel by viewModels()
    private lateinit var adaptermars: RoverPhotosAdapter
    private lateinit var adapternasa: NasaAdapter
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

    private fun loadSearch() {

        lifecycleScope.launch {
            viewModel.searchNasa.collect {
                Log.d("aaa", "Data Loaded: $it")
                adapternasa.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_character, menu)
        val item = menu.findItem(R.id.searchCharacterMenu)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchNasaImages(query)
                    loadSearch()

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchNasaImages(newText)
                }
                return true
            }

        })
        return true
    }


    private fun setUpRv() {
        adapternasa = NasaAdapter()
        adaptermars = RoverPhotosAdapter()

        binding.recyclerviewMars.apply {
        adapter =adaptermars
        layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }

        binding.recyclerviewNasaSearch.apply {
            adapter = adapternasa
            layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }


    }


    private fun setUpViewModel() {

            lifecycleScope.launch {
                viewModel.marsphotos.collect {
                    Log.d("aaa", "Data Loaded: $it")
                    adaptermars.submitData(it)
                }
            }
        }
    }

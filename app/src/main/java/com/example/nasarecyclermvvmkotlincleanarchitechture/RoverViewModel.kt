package com.example.nasarecyclermvvmkotlincleanarchitechture


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class RoverViewModel @Inject constructor(private val moviesApiServiceMars: ApiServiceMars) : ViewModel() {

    val marsphotos = Pager(PagingConfig(pageSize = 20, prefetchDistance =50,)) {
        MoviePagingSource(moviesApiServiceMars)
    }.flow.cachedIn(viewModelScope)
}
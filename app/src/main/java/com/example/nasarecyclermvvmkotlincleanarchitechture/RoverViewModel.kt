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

  /*  private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    fun getPhotos(sol: Int,page:Int, apikey: String) {
        viewModelScope.launch {
            val result = repository.getPhotos(sol, page,apikey)
            if (result is Result.Success) {
                _photos.value = result.data.photos
            }
        }
    }*/
    val marsphotos = Pager(PagingConfig(pageSize = 10)) {
        MoviePagingSource(moviesApiServiceMars)
    }.flow.cachedIn(viewModelScope)
}
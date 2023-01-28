package com.example.nasarecyclermvvmkotlincleanarchitechture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RoverViewModel @Inject constructor(private val repository: MarsRepository) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    fun getPhotos(sol: Int,page:Int, apikey: String) {
        viewModelScope.launch {
            val result = repository.getPhotos(sol, page,apikey)
            if (result is Result.Success) {
                _photos.value = result.data.photos
            }
        }
    }
}
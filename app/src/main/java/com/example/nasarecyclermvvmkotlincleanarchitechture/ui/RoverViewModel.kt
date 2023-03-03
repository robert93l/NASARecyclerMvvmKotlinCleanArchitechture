package com.example.nasarecyclermvvmkotlincleanarchitechture.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nasarecyclermvvmkotlincleanarchitechture.api.ApiServiceMars
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.nasa.Item
import com.example.nasarecyclermvvmkotlincleanarchitechture.paging.MarsPagingSource
import com.example.nasarecyclermvvmkotlincleanarchitechture.paging.NasaPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class RoverViewModel @Inject constructor(
    private var query: String, @Named("apiServiceMars1") private val apiService1: ApiServiceMars,
    @Named("apiServiceMars2") private val apiService2: ApiServiceMars
) : ViewModel() {

    val marsphotos = Pager(PagingConfig(pageSize = 20, prefetchDistance = 50)) {
        MarsPagingSource(apiService1)
    }.flow.cachedIn(viewModelScope)


    var searchNasa: Flow<PagingData<Item>> = emptyFlow()
    fun searchNasaImages(query: String) {
        this.query = query
        searchNasa = Pager(PagingConfig(pageSize = 20)) {
            NasaPagingSource(apiService2, this.query)
        }.flow.cachedIn(viewModelScope)
    }
}
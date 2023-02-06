package com.example.nasarecyclermvvmkotlincleanarchitechture.api

import com.example.nasarecyclermvvmkotlincleanarchitechture.utils.Constants
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.photosreponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceMars {

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsPhotos(
        @Query("sol") sol: Int ,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY

    ): Response<photosreponse>
}
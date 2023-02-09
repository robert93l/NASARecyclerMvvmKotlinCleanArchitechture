package com.example.nasarecyclermvvmkotlincleanarchitechture.di

import com.example.nasarecyclermvvmkotlincleanarchitechture.utils.Constants
import com.example.nasarecyclermvvmkotlincleanarchitechture.api.ApiServiceMars
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    val gson = GsonBuilder()
        .setLenient()
        .create()
    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiServiceMars =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceMars::class.java)
}
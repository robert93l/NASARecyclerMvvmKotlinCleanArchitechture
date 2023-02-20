package com.example.nasarecyclermvvmkotlincleanarchitechture.di

import com.example.nasarecyclermvvmkotlincleanarchitechture.utils.Constants
import com.example.nasarecyclermvvmkotlincleanarchitechture.api.ApiServiceMars
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl
annotation class BaseUrl2

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @BaseUrl
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    @Named("apiServiceMars1")
    fun provideRetrofitInstance(@BaseUrl baseUrl: String): ApiServiceMars =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceMars::class.java)

    @Provides
    @BaseUrl2
    fun provideBaseUrl2() = Constants.BASE_URL_2

    @Provides
    @Singleton
    @Named("apiServiceMars2")
    fun provideRetrofitInstance2(@BaseUrl2 baseUrl: String): ApiServiceMars =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceMars::class.java)
}
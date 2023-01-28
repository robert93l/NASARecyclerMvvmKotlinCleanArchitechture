package com.example.nasarecyclermvvmkotlincleanarchitechture
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.photosreponse
import javax.inject.Inject

class MarsRepository @Inject constructor(private val apiService: ApiServiceMars) {
    suspend fun getPhotos(sol: Int, page: Int, apikey:String): Result<photosreponse> {
        return try {
            val response = apiService.getMarsPhotos(sol, page,apikey)
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: e.toString())
        }
    }
}
package com.example.nasarecyclermvvmkotlincleanarchitechture

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.Photo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val MOVIES_STARTING_PAGE_INDEX = 1

class MoviePagingSource @Inject constructor(
    private val movieService: ApiServiceMars,
) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {

        return try {
            val currentPageList = params.key ?: MOVIES_STARTING_PAGE_INDEX
            val response = movieService.getMarsPhotos(
                1000,
                currentPageList,
                Constants.API_KEY,
            )

            val responseList = mutableListOf<Photo>()

            if (response.isSuccessful) {
                val data = response.body()?.photos ?: emptyList()
                responseList.addAll(data)
            }

            LoadResult.Page(
                data = responseList,
                prevKey = if (currentPageList == MOVIES_STARTING_PAGE_INDEX) null else currentPageList - 1,
                nextKey = currentPageList.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
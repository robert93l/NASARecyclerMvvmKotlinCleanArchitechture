package com.example.nasarecyclermvvmkotlincleanarchitechture.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nasarecyclermvvmkotlincleanarchitechture.api.ApiServiceMars
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.nasa.Item
import com.example.nasarecyclermvvmkotlincleanarchitechture.utils.Constants
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.nasa.Link
import com.example.nasarecyclermvvmkotlincleanarchitechture.data.nasa.LinkX
import com.example.nasarecyclermvvmkotlincleanarchitechture.utils.Constants.MOVIES_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class NasaPagingSource @Inject constructor(
    @Named("apiServiceMars2") private val movieService: ApiServiceMars,
    private val query: String
) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        return try {
            val currentPageList = params.key ?: MOVIES_STARTING_PAGE_INDEX
            val response = movieService.getSearchNasapictures(
                querysearch = query,
                currentPageList,
                Constants.IMAGE
            )


            if (response.isSuccessful) {
                val items = response.body()?.collection?.items ?: emptyList()
                Log.d("NasaPagingSource", "Response data: $items")
                LoadResult.Page(
                    data = items,
                    prevKey = if (currentPageList == 1) null else currentPageList - 1,
                    nextKey = if (items.isNotEmpty()) currentPageList + 1 else null
                )

            }else {
                Log.e("NasaPagingSource", "Failed to fetch data: ${response.message()}")
                LoadResult.Error(Throwable("Failed to fetch data"))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
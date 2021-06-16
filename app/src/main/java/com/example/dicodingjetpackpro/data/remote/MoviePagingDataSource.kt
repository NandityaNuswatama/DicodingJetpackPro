package com.example.dicodingjetpackpro.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.model.MyModel
import com.example.dicodingjetpackpro.utils.DataMapper
import com.example.dicodingjetpackpro.utils.EspressoIdlingResource

class MoviePagingDataSource(private val apiService: ApiService): PagingSource<Int, MyModel>() {
    override fun getRefreshKey(state: PagingState<Int, MyModel>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyModel> {
        EspressoIdlingResource.increment()
        return try {
            val page: Int = params.key ?: 1
            val response = apiService.getMovieList(BuildConfig.API_KEY, page)
            val loadResult = LoadResult.Page(
                DataMapper.mapMovieListToModel(response.body()),
                if (page == 1) null else page - 1,
                response.body()?.page?.plus(1)
            )
            EspressoIdlingResource.decrement()
            return loadResult
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}
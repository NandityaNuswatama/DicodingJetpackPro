package com.example.dicodingjetpackpro.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dicodingjetpackpro.data.MyRepository
import com.example.dicodingjetpackpro.data.remote.ApiService
import com.example.dicodingjetpackpro.data.remote.MoviePagingDataSource
import com.example.dicodingjetpackpro.data.remote.ShowPagingDataSource
import com.example.dicodingjetpackpro.model.MyModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val apiService: ApiService, myRepository: MyRepository): ViewModel() {

    val getMovieList = myRepository.getMovieList()
    val getShowList = myRepository.getShowList()

    fun getMovieList(): Flow<PagingData<MyModel>> =
        Pager(PagingConfig(20))
        {MoviePagingDataSource(apiService)}
            .flow.cachedIn(viewModelScope)

    fun getShowList(): Flow<PagingData<MyModel>> =
        Pager(PagingConfig(20))
        {ShowPagingDataSource(apiService)}
            .flow.cachedIn(viewModelScope)
}
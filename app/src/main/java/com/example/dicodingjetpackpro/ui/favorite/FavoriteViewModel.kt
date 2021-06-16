package com.example.dicodingjetpackpro.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.dicodingjetpackpro.data.MyRepository
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val myRepository: MyRepository): ViewModel() {
    fun getFavoriteMovies(): LiveData<PagedList<MyModelEntity>> =
        LivePagedListBuilder(myRepository.getMoviesList(), 20).build()
    fun getFavMovieTest(): LiveData<ArrayList<MyModelEntity>>{
        val liveArray = MutableLiveData<ArrayList<MyModelEntity>>()
        val array = ArrayList<MyModelEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            array.addAll(myRepository.getMoviesListTest())
            liveArray.postValue(array)
        }
        return liveArray
    }
    fun getFavoriteShows(): LiveData<PagedList<MyModelEntity>> =
        LivePagedListBuilder(myRepository.getShowsList(), 20).build()
    fun getFavShowTest(): LiveData<ArrayList<MyModelEntity>>{
        val liveArray = MutableLiveData<ArrayList<MyModelEntity>>()
        val array = ArrayList<MyModelEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            array.addAll(myRepository.getShowListTest())
            liveArray.postValue(array)
        }
        return liveArray
    }
}
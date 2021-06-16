package com.example.dicodingjetpackpro.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingjetpackpro.data.local.LocalDataSource
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import com.example.dicodingjetpackpro.data.remote.RemoteDataSource
import com.example.dicodingjetpackpro.model.MyModel
import com.example.dicodingjetpackpro.utils.DataMapper
import com.example.dicodingjetpackpro.utils.Dummy

class FakeRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) {
    fun getMovieList(): LiveData<ArrayList<MyModel>> {
        val dummyMovies = Dummy.dummyMovies()
        val movies = MutableLiveData<ArrayList<MyModel>>()
        movies.value = dummyMovies
        return movies
    }

    fun getShowList(): LiveData<ArrayList<MyModel>> {
        val dummyShows = Dummy.dummyShows()
        val show = MutableLiveData<ArrayList<MyModel>>()
        show.value = dummyShows
        return show
    }

    fun getMoviesList(): List<MyModelEntity>{
        val dummy = Dummy.dummyMovies()[0]
        return listOf(DataMapper.mapModelToEntity(dummy))
    }

    fun getShowsList(): List<MyModelEntity>{
        val dummy = Dummy.dummyShows()[0]
        return listOf(DataMapper.mapModelToEntity(dummy))
    }

    fun getMovieListRepo(): LiveData<ArrayList<MyModel>> {
        val movies = MutableLiveData<ArrayList<MyModel>>()
        remoteDataSource.getMovieList(object : RemoteDataSource.ModelCallback{
            override fun onReceived(model: ArrayList<MyModel>) {
                movies.postValue(model)
            }
        })
        return movies
    }

    fun getShowListRepo(): LiveData<ArrayList<MyModel>> {
        val show = MutableLiveData<ArrayList<MyModel>>()
        remoteDataSource.getShowList(object : RemoteDataSource.ModelCallback{
            override fun onReceived(model: ArrayList<MyModel>) {
                show.postValue(model)
            }
        })
        return show
    }

    fun getDetailMovie(): LiveData<MyModel>{
        val detailDummy = Dummy.dummyMovies()[0]
        val detail = MutableLiveData<MyModel>()
        detail.value = (detailDummy)
        return detail
    }

    fun getDetailShow(): LiveData<MyModel>{
        val detailDummy = Dummy.dummyShows()[0]
        val detail = MutableLiveData<MyModel>()
        detail.value = (detailDummy)
        return detail
    }

    fun getDetailMovieRepo(): LiveData<MyModel>{
        val movieId = 804435
        val mDetail = MutableLiveData<MyModel>()
        remoteDataSource.getDetailMovie(object : RemoteDataSource.DetailCallback{
            override fun onReceived(detail: MyModel) {
                mDetail.postValue(detail)
            }
        }, movieId)
        return mDetail
    }

    fun getDetailShowRepo(): LiveData<MyModel>{
        val showId = 88396
        val mDetail = MutableLiveData<MyModel>()
        remoteDataSource.getDetailShow(object : RemoteDataSource.DetailCallback{
            override fun onReceived(detail: MyModel) {
                mDetail.postValue(detail)
            }
        }, showId)
        return mDetail
    }
}
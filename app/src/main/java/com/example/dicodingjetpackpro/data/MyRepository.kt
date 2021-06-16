package com.example.dicodingjetpackpro.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingjetpackpro.data.local.LocalDataSource
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import com.example.dicodingjetpackpro.data.remote.RemoteDataSource
import com.example.dicodingjetpackpro.model.MyCast
import com.example.dicodingjetpackpro.model.MyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) {

     fun getMovieList(): LiveData<ArrayList<MyModel>>{
          val movie = MutableLiveData<ArrayList<MyModel>>()
          remoteDataSource.getMovieList(object : RemoteDataSource.ModelCallback{
               override fun onReceived(model: ArrayList<MyModel>) {
                    movie.postValue(model)
               }
          })
          return movie
     }
     fun getShowList(): LiveData<ArrayList<MyModel>>{
          val show = MutableLiveData<ArrayList<MyModel>>()
          remoteDataSource.getShowList(object : RemoteDataSource.ModelCallback{
               override fun onReceived(model: ArrayList<MyModel>) {
                    show.postValue(model)
               }
          })
          return show
     }
     fun getDetailMovie(id: Int): LiveData<MyModel>{
          val movie = MutableLiveData<MyModel>()
          remoteDataSource.getDetailMovie(object : RemoteDataSource.DetailCallback{
               override fun onReceived(detail: MyModel) {
                    movie.postValue(detail)
               }
          }, id)
          return movie
     }
     fun getDetailShow(id: Int): LiveData<MyModel>{
          val show = MutableLiveData<MyModel>()
          remoteDataSource.getDetailShow(object : RemoteDataSource.DetailCallback{
               override fun onReceived(detail: MyModel) {
                    show.postValue(detail)
               }
          }, id)
          return show
     }
     fun getActorMovie(id: Int): LiveData<ArrayList<MyCast>>{
          val mActor = MutableLiveData<ArrayList<MyCast>>()
          remoteDataSource.getActorMovie(object : RemoteDataSource.ActorCallback{
               override fun onReceived(actor: ArrayList<MyCast>) {
                    mActor.postValue(actor)
               }
          }, id)
          return mActor
     }
     fun getActorShow(id: Int): LiveData<ArrayList<MyCast>>{
          val mActor = MutableLiveData<ArrayList<MyCast>>()
          remoteDataSource.getActorShow(object : RemoteDataSource.ActorCallback{
               override fun onReceived(actor: ArrayList<MyCast>) {
                    mActor.postValue(actor)
               }
          }, id)
          return mActor
     }

     fun getMoviesList() = localDataSource.getFavoriteMovie()
     fun getShowsList() = localDataSource.getFavoriteShow()
     fun getMoviesListTest() = localDataSource.getFavoriteMovieTest()
     fun getShowListTest() = localDataSource.getFavoriteShowTest()
     fun isFavorite(id: Int, category: Int): LiveData<Boolean> = localDataSource.isFavorite(id, category)
     fun insertItem(item: MyModelEntity){
          CoroutineScope(Dispatchers.IO).launch {
               localDataSource.insertItem(item)
          }
     }
     fun deleteById(id: Int, category: Int){
          CoroutineScope(Dispatchers.IO).launch {
               localDataSource.deleteById(id, category)
          }
     }
     
}
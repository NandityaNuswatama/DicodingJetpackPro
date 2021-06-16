package com.example.dicodingjetpackpro.data.remote

import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.model.MyCast
import com.example.dicodingjetpackpro.model.MyModel
import com.example.dicodingjetpackpro.utils.DataMapper
import com.example.dicodingjetpackpro.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class RemoteDataSource(private val apiService: ApiService) {
    fun getMovieList(callback: ModelCallback){
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getMovieList(BuildConfig.API_KEY, 1)
                if (response.isSuccessful){
                    val data = response.body()
                    callback.onReceived(DataMapper.mapMovieListToModel(data))
                    EspressoIdlingResource.decrement()
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }
    fun getShowList(callback: ModelCallback){
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getShowList(BuildConfig.API_KEY, 1)
                if (response.isSuccessful){
                    val data = response.body()
                    callback.onReceived(DataMapper.mapShowsToModel(data))
                    EspressoIdlingResource.decrement()
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }

    fun getDetailMovie(callback: DetailCallback, id: Int){
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getDetailMovie(id, BuildConfig.API_KEY)
                if (response.isSuccessful){
                    val data = response.body()
                    callback.onReceived(DataMapper.mapDetailMovieToModel(data))
                    EspressoIdlingResource.decrement()
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }

    fun getDetailShow(callback: DetailCallback, id: Int){
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getDetailShow(id, BuildConfig.API_KEY)
                if (response.isSuccessful){
                    val data = response.body()
                    callback.onReceived(DataMapper.mapDetailShowToModel(data))
                    EspressoIdlingResource.decrement()
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }

    fun getActorMovie(callback: ActorCallback, id: Int){
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getActorsListMovie(id, BuildConfig.API_KEY)
                if (response.isSuccessful){
                    val data = response.body()
                    callback.onReceived(DataMapper.mapActorsToCast(data))
                    EspressoIdlingResource.decrement()
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }

    fun getActorShow(callback: ActorCallback, id: Int){
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getActorsListShow(id, BuildConfig.API_KEY)
                if (response.isSuccessful){
                    val data = response.body()
                    callback.onReceived(DataMapper.mapActorsToCast(data))
                    EspressoIdlingResource.decrement()
                }
            }catch (e: Exception){
                Timber.e(e)
            }
        }
    }

    interface ModelCallback {
        fun onReceived(model: ArrayList<MyModel>)
    }

    interface DetailCallback {
        fun onReceived(detail: MyModel)
    }
    interface ActorCallback {
        fun onReceived(actor: ArrayList<MyCast>)
    }
}
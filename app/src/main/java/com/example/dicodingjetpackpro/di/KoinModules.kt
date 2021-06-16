package com.example.dicodingjetpackpro.di

import androidx.room.Room
import com.example.dicodingjetpackpro.data.MyRepository
import com.example.dicodingjetpackpro.data.local.LocalDataSource
import com.example.dicodingjetpackpro.data.local.room.MyModelDatabase
import com.example.dicodingjetpackpro.data.remote.ApiService
import com.example.dicodingjetpackpro.data.remote.MoviePagingDataSource
import com.example.dicodingjetpackpro.data.remote.RemoteDataSource
import com.example.dicodingjetpackpro.data.remote.ShowPagingDataSource
import com.example.dicodingjetpackpro.ui.RecyclerViewAdapter
import com.example.dicodingjetpackpro.ui.detail.ActorsAdapter
import com.example.dicodingjetpackpro.ui.detail.DetailViewModel
import com.example.dicodingjetpackpro.ui.detail.GenreAdapter
import com.example.dicodingjetpackpro.ui.favorite.FavAdapter
import com.example.dicodingjetpackpro.ui.favorite.FavoriteViewModel
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module{
    single {
        Room.databaseBuilder(
            androidContext(), MyModelDatabase::class.java, "MyModel.db"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<MyModelDatabase>().myModelDao() }
}

val repositoryModule = module {
    single { MoviePagingDataSource(get()) }
    single { ShowPagingDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single { MyRepository(get(), get()) }
}

val viewModelModule = module {
    single { HomeViewModel(get(), get()) }
    single { DetailViewModel(get()) }
    single { FavoriteViewModel(get()) }
}

val adapterModule = module {
    factory { RecyclerViewAdapter() }
    factory { GenreAdapter() }
    factory { ActorsAdapter() }
    factory { FavAdapter() }
}
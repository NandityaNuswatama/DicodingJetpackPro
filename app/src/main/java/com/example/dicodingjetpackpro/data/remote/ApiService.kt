package com.example.dicodingjetpackpro.data.remote

import com.example.dicodingjetpackpro.model.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>

    @GET("tv/popular")
    suspend fun getShowList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<ShowsListResponse>

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailMovieResponse>

    @GET("tv/{id}")
    suspend fun getDetailShow(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailShowsResponse>

    @GET("movie/{id}/credits")
    suspend fun getActorsListMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<ActorsResponse>
    
    @GET("tv/{id}/credits")
    suspend fun getActorsListShow(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<ActorsResponse>
}
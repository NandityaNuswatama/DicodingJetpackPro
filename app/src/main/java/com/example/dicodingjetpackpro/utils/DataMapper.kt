package com.example.dicodingjetpackpro.utils

import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import com.example.dicodingjetpackpro.model.MyCast
import com.example.dicodingjetpackpro.model.MyModel
import com.example.dicodingjetpackpro.model.response.*

object DataMapper {
    fun mapMovieListToModel(input: MovieListResponse?): ArrayList<MyModel>{
        val myModel = ArrayList<MyModel>()
        input?.results?.map {
            myModel.add(MyModel(
                it.id,
                it.title,
                it.posterPath,
                it.backdropPath,
                it.overview,
                it.voteAverage,
                it.releaseDate,
                it.originalTitle,
                listOf(it.genreIds.toString())
            )
            )
        }
        return myModel
    }
    fun mapShowsToModel(input: ShowsListResponse?): ArrayList<MyModel>{
        val myModel = ArrayList<MyModel>()
        input?.results?.map {
            myModel.add(MyModel(
                it.id,
                it.name,
                it.posterPath,
                it.backdropPath,
                it.overview,
                it.voteAverage,
                it.firstAirDate,
                it.name,
                listOf(it.genreIds.toString())
            )
            )
        }
        return myModel
    }
    fun mapDetailMovieToModel(input: DetailMovieResponse?): MyModel{
        return MyModel(
            id = input?.id,
            title = input?.title,
            poster = input?.posterPath,
            background = input?.backdropPath,
            overview = input?.overview,
            rating = input?.voteAverage,
            date = input?.releaseDate,
            runtime = input?.runtime.toString(),
            genre = input?.genres?.map { it.name!! }
        )
    }
    fun mapDetailShowToModel(input: DetailShowsResponse?): MyModel{
        return MyModel(
            id = input?.id,
            title = input?.name,
            poster = input?.posterPath,
            background = input?.backdropPath,
            overview = input?.overview,
            rating = input?.voteAverage,
            date = input?.firstAirDate,
            runtime = input?.episodeRunTime?.firstOrNull().toString(),
            genre = input?.genres?.map { it.name!! }
        )
    }
    fun mapActorsToCast(input: ActorsResponse?): ArrayList<MyCast>{
        val myCast = ArrayList<MyCast>()
        input?.cast?.map {
            myCast.add(MyCast(
                it.name, it.profilePath, it.character
            ))
        }
        return myCast
    }
    fun mapEntityToModel(input: MyModelEntity): MyModel{
        return MyModel(
            input.id,
            input.title,
            input.poster,
            input.background,
            null,
            input.rating,
            input.date,
            null,
            null
        )
    }
    fun mapModelToEntity(input: MyModel):MyModelEntity{
        return MyModelEntity(
            input.id,
            input.title,
            input.poster,
            input.background,
            input.rating,
            input.date,
            101
        )
    }
}
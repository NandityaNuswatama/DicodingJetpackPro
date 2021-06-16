package com.example.dicodingjetpackpro.data.local

import androidx.lifecycle.LiveData
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import com.example.dicodingjetpackpro.data.local.room.MyModelDao
import com.example.dicodingjetpackpro.utils.MOVIE
import com.example.dicodingjetpackpro.utils.SHOW

class LocalDataSource(private val myModelDao: MyModelDao) {
    fun getFavoriteMovie() = myModelDao.getFavoriteList(MOVIE)
    fun getFavoriteMovieTest() = myModelDao.getFavoriteListTest(MOVIE)
    fun getFavoriteShow() = myModelDao.getFavoriteList(SHOW)
    fun getFavoriteShowTest() = myModelDao.getFavoriteListTest(SHOW)
    fun isFavorite(id: Int, category: Int): LiveData<Boolean> = myModelDao.isFavorite(id, category)
    suspend fun insertItem(item: MyModelEntity){
        myModelDao.insertModel(item)
    }
    suspend fun deleteById(id: Int, category: Int){
        myModelDao.deleteById(id, category)
    }
}
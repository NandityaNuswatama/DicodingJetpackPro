package com.example.dicodingjetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.example.dicodingjetpackpro.data.MyRepository
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity

class DetailViewModel(private val myRepository: MyRepository): ViewModel() {

    fun getDetailMovie(id: Int) = myRepository.getDetailMovie(id)
    
    fun getDetailShow(id: Int) = myRepository.getDetailShow(id)
    
    fun getActorsMovie(id: Int) = myRepository.getActorMovie(id)
    
    fun getActorsShow(id: Int) = myRepository.getActorShow(id)

    fun isFavorite(id: Int, category: Int) = myRepository.isFavorite(id, category)

    fun insertToDb(item: MyModelEntity) = myRepository.insertItem(item)

    fun deleteById(id: Int, category: Int) = myRepository.deleteById(id, category)
}
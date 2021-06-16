package com.example.dicodingjetpackpro.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

const val MOVIE = 101
const val SHOW = 110

fun imageLink(): String = "https://image.tmdb.org/t/p/w500"

class DiffUtilCallback(private val oldItem: List<Any>, private val newItem: List<Any>): DiffUtil.Callback(){
    override fun getOldListSize() = oldItem.size

    override fun getNewListSize() = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] == newItem[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)
    
    fun increment() {
        espressoTestIdlingResource.increment()
    }
    
    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
    
    fun getEspressoIdlingResource(): IdlingResource = espressoTestIdlingResource
}


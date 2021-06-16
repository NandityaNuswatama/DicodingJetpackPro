package com.example.dicodingjetpackpro.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity

@Database(entities = [MyModelEntity::class], version = 1, exportSchema = false)
abstract class MyModelDatabase: RoomDatabase() {
    abstract fun myModelDao(): MyModelDao
}
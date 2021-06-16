package com.example.dicodingjetpackpro.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity

@Dao
interface MyModelDao {
    @Insert(entity = MyModelEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertModel(item: MyModelEntity)

    @Query("SELECT * FROM my_model_table WHERE category=:category")
    fun getFavoriteList(category: Int): DataSource.Factory<Int, MyModelEntity>

    @Query("SELECT * FROM my_model_table WHERE category=:category")
    fun getFavoriteListTest(category: Int): List<MyModelEntity>

    @Query("SELECT EXISTS (SELECT * FROM my_model_table WHERE id=:id AND category=:category)")
    fun isFavorite(id: Int, category: Int): LiveData<Boolean>

    @Query("DELETE FROM my_model_table WHERE id=:id AND category=:category")
    suspend fun deleteById(id: Int, category: Int)
}
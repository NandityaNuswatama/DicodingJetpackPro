package com.example.dicodingjetpackpro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_model_table")
data class MyModelEntity(
    @PrimaryKey
    var id: Int?= 0,
    val title: String?= "",
    val poster: String?= "",
    val background: String?= "",
    val rating: Double?= 0.0,
    val date: String?= "",
    val category: Int
)

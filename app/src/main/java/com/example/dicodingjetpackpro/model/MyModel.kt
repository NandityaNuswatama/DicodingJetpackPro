package com.example.dicodingjetpackpro.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyModel(
    var id: Int?= 0,
    val title: String?= "",
    val poster: String?= "",
    val background: String?= "",
    val overview: String?= "",
    val rating: Double?= 0.0,
    val date: String?= "",
    val runtime: String?= "",
    val genre: List<String>?= emptyList()
): Parcelable

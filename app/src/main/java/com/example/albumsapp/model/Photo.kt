package com.example.albumsapp.model

import com.squareup.moshi.Json

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String
)

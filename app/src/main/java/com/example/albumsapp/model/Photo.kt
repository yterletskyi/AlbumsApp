package com.example.albumsapp.model

import com.squareup.moshi.Json

data class Photo(val id: String, @Json(name ="img_src") val imgSrc: String)

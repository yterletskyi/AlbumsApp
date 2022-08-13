package com.example.albumsapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String
) : Parcelable

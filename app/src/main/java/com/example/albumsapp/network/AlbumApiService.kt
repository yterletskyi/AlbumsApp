package com.example.albumsapp.network

import com.example.albumsapp.model.Album
import com.example.albumsapp.model.Photo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface AlbumApiService {
    @GET("Albums")
     fun getAlbums() : List<Album>

    @GET("{albumId}/photos")
     fun getPhotos() : List<Photo>
}

object AlbumsApi {
    val retrofitService : AlbumApiService by lazy {
        retrofit.create(AlbumApiService::class.java)
    }
}

package com.example.albumsapp.network

import com.example.albumsapp.model.Album
import com.example.albumsapp.model.Photo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface AlbumApiService {
    @GET("/albums")
     suspend fun getAlbums() : List<Album>

    @GET("/albums/{albumId}/photos")
     suspend fun getPhotos(@Path("albumId") albumId: Int) : List<Photo>
}

object AlbumsApi {
    val retrofitService : AlbumApiService by lazy {
        retrofit.create(AlbumApiService::class.java)
    }
}

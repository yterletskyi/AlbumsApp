package com.example.albumsapp.network

import com.example.albumsapp.model.Album
import com.example.albumsapp.model.Photo
import retrofit2.http.GET
import retrofit2.http.Path


interface AlbumApiService {
    @GET("/albums")
    suspend fun getAlbums(): List<Album>

    @GET("/albums/{albumId}/photos")
    suspend fun getPhotos(@Path("albumId") albumId: Int): List<Photo>
}

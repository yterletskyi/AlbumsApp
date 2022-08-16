package com.example.albumsapp.network

import com.example.albumsapp.model.Album
import com.example.albumsapp.model.Photo
import javax.inject.Inject

interface DataSource {
    suspend fun getAlbums(): List<Album>
    suspend fun getPhotos(albumId: Int): List<Photo>
}

class ApiDataSource @Inject constructor(
    private val retrofitService: AlbumApiService,
) : DataSource {

    override suspend fun getAlbums(): List<Album> {
        return retrofitService.getAlbums()
    }

    override suspend fun getPhotos(albumId: Int): List<Photo> {
        return retrofitService.getPhotos(albumId)
    }
}

class FakeDataSource : DataSource {

    override suspend fun getAlbums(): List<Album> = listOf(
        Album(1, 1, "Album 1"),
        Album(1, 2, "Album 2"),
        Album(1, 3, "Album 3"),
        Album(1, 4, "Album 4"),
    )

    override suspend fun getPhotos(albumId: Int): List<Photo> = listOf(
        Photo(1, 2, "Blue Beautiful Photo", "/aaffff", "/aaffff"),
    )
}

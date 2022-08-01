package com.example.albumsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.albumsapp.model.Album
import com.example.albumsapp.network.AlbumsApi

class AlbumViewModel : ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()

    val albums : LiveData<List<Album>> = _albums

    init {
        getAlbums()
    }

    private fun getAlbums() {
        try {
            _albums.value = AlbumsApi.retrofitService.getAlbums()
        }catch(e : Exception) {
            _albums.value = listOf()
        }
    }
}
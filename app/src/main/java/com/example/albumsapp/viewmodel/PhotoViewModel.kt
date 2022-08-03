package com.example.albumsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumsapp.model.Photo
import com.example.albumsapp.network.AlbumsApi
import kotlinx.coroutines.launch


class PhotoViewModel() : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()

    val photos : LiveData<List<Photo>> = _photos

    init {
        getPhotos(1)
    }

    private fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            try {
                _photos.value = AlbumsApi.retrofitService.getPhotos(albumId)
            }catch (e : Exception) {
            }
        }
    }
}
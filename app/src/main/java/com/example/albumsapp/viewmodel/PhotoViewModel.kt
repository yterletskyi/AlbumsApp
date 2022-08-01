package com.example.albumsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.albumsapp.model.Photo
import com.example.albumsapp.network.AlbumsApi


class PhotoViewModel : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()

    val photos : LiveData<List<Photo>> = _photos

    init {

    }

    private fun getPhotos(index: String) {
        try {
            _photos.value = AlbumsApi.retrofitService.getPhotos(index)
        } catch (e : Exception) {
            _photos.value = listOf()
        }
    }
}
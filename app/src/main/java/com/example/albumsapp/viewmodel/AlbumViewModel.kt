package com.example.albumsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumsapp.model.Album
import com.example.albumsapp.network.AlbumsApi
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>> = _albums

    private val _errorLiveData = MutableLiveData<Exception>()

    val errorLiveData: LiveData<Exception> = _errorLiveData

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            try {
                _albums.value = AlbumsApi.retrofitService.getAlbums()
            } catch (e: Exception) {
                _errorLiveData.postValue(e)
            }
        }
    }
}

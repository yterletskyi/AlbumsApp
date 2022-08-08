package com.example.albumsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.albumsapp.model.Photo
import com.example.albumsapp.network.AlbumsApi
import kotlinx.coroutines.launch

class PhotoViewModel(val id: Int) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()

    val photos: LiveData<List<Photo>> = _photos

    private val _errorLiveData = MutableLiveData<Exception>()

    val errorLiveData: LiveData<Exception> = _errorLiveData

    class MyViewModelFactory(
        private val id: Int
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PhotoViewModel(id) as T
        }
    }

    init {
        getPhotos(id)
    }

    private fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            try {
                _photos.value = AlbumsApi.retrofitService.getPhotos(albumId)
            } catch (e: Exception) {
                _errorLiveData.postValue(e)
            }
        }
    }
}

package com.example.albumsapp.viewmodel

import androidx.lifecycle.*
import com.example.albumsapp.model.Photo
import com.example.albumsapp.network.AlbumsApi
import kotlinx.coroutines.launch


class PhotoViewModel(val id: String) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()

    val photos: LiveData<List<Photo>> = _photos

    class MyViewModelFactory(
        private val id: String
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PhotoViewModel(id) as T
        }
    }

    init {
        getPhotos(1)
    }

    private fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            try {
                _photos.value = AlbumsApi.retrofitService.getPhotos(albumId)
            } catch (e: Exception) {
            }
        }
    }
}
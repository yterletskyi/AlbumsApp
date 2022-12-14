package com.example.albumsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.albumsapp.model.Photo
import com.example.albumsapp.network.DataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoViewModel(
    private val id: Int,
    private val apiDataSource: DataSource,
) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()

    val photos: LiveData<List<Photo>> = _photos

    private val _errorLiveData = MutableLiveData<Exception>()

    val errorLiveData: LiveData<Exception> = _errorLiveData

    class MyViewModelFactory @Inject constructor(
        private val id: Int,
        private val apiDataSource: DataSource,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PhotoViewModel(id, apiDataSource) as T
        }
    }

    init {
        getPhotos(id)
    }

    private fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            try {
                _photos.value = apiDataSource.getPhotos(albumId)
            } catch (e: Exception) {
                _errorLiveData.postValue(e)
            }
        }
    }
}

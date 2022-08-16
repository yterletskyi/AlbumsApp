package com.example.albumsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlbumApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
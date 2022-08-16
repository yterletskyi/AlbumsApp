package com.example.albumsapp

import com.example.albumsapp.network.AlbumApiService
import com.example.albumsapp.network.ApiDataSource
import com.example.albumsapp.network.DataSource
import com.example.albumsapp.parser.PhotoColorParser
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideApiDataSource(retrofitService: AlbumApiService): ApiDataSource {
        return ApiDataSource(retrofitService)
    }

    @Provides
    fun provideColorParser(): PhotoColorParser {
        return PhotoColorParser()
    }

    @Provides
    fun bindAlbumService(retrofit: Retrofit): AlbumApiService {
        return retrofit.create(AlbumApiService::class.java)
    }

    @Provides
    fun bindDataSource(retrofitService: AlbumApiService): DataSource {
        return ApiDataSource(retrofitService)
    }
}

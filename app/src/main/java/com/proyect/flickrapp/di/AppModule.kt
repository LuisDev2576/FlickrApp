package com.proyect.flickrapp.di

import androidx.paging.ExperimentalPagingApi
import com.proyect.flickrapp.data.remote.FlickrApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideBeerApi(): FlickrApi {
        return Retrofit.Builder()
            .baseUrl(FlickrApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

}
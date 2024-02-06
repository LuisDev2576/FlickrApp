package com.proyect.flickrapp.di

import android.app.Application
import androidx.room.Room
import com.proyect.flickrapp.data.local.PhotosDatabase
import com.proyect.flickrapp.data.remote.FlickrApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesFlickrApi() : FlickrApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(FlickrApi.BASE_URL)
            .client(client)
            .build()
            .create(FlickrApi::class.java)
    }

    @Provides
    @Singleton
    fun providesFlickrDatabase(app: Application): PhotosDatabase {
        return Room.databaseBuilder(
            app,
            PhotosDatabase::class.java,
            "photosDatabase.db"
        ).build()
    }

}





















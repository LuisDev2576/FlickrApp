package com.ahmedapps.moviesapp.di

import com.proyect.flickrapp.data.repository.PhotoListRepositoryImpl
import com.proyect.flickrapp.domain.repository.PhotoListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        movieListRepositoryImpl: PhotoListRepositoryImpl
    ): PhotoListRepository

}













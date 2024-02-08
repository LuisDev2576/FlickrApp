package com.proyect.flickrapp.presentation.popularPhotos

import com.proyect.flickrapp.domain.model.Photo

data class MovieListState(
    val isLoading: Boolean = false,

    val popularMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true,

    val popularPhotoList: List<Photo> = emptyList(),
)
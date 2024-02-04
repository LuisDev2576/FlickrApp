package com.ahmedapps.moviesapp.movieList.data.remote.respnod

data class MovieListDto(
    val photos: Photos,
    val stat: String,
)

data class Photos(
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val total: Int,
    val photo: List<MovieDto>,
)
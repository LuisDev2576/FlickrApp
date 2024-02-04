package com.ahmedapps.moviesapp.movieList.data.mappers

import com.ahmedapps.moviesapp.movieList.data.remote.respnod.MovieDto
import com.ahmedapps.moviesapp.movieList.domain.model.Movie

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */


fun MovieDto.toMovie(): Movie {
    return Movie(
        adult = adult ?: false,
        backdrop_path = backdrop_path ?: "",
        original_language = original_language ?: "",
        overview = overview ?: "",
        poster_path = poster_path ?: "",
        release_date = release_date ?: "",
        title = title ?: "",
        vote_average = vote_average ?: 0.0,
        popularity = popularity ?: 0.0,
        vote_count = vote_count ?: 0,
        id = id ?: -1,
        original_title = original_title ?: "",
        video = video ?: false,

        category = "category",

        genre_ids = genre_ids?: listOf(1)
    )
}





















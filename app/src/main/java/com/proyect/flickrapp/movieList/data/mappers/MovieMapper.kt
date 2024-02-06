package com.ahmedapps.moviesapp.movieList.data.mappers

import com.ahmedapps.moviesapp.movieList.data.local.movie.MovieEntity
import com.ahmedapps.moviesapp.movieList.data.remote.respnod.MovieDto
import com.ahmedapps.moviesapp.movieList.domain.model.Movie

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */


fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        owner = owner,
        secret = secret,
        server = server,
        farm = farm,
        title = title,
        isPublic = isPublic,
        isFriend = isFriend,
        isFamily = isFamily,
        date_faved = date_faved,
        url_c = url_c,
        height_c = height_c,
        width_c = width_c,
        page = page
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        owner = owner,
        secret = secret,
        server = server,
        farm = farm,
        title = title,
        isPublic = isPublic,
        isFriend = isFriend,
        isFamily = isFamily,
        dateFaved = date_faved,
        urlC = url_c,
        heightC = height_c,
        widthC = width_c,
        page = page
    )
}





















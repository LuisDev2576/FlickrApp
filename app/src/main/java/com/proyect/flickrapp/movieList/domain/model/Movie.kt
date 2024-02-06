package com.ahmedapps.moviesapp.movieList.domain.model

import androidx.room.PrimaryKey

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
data class Movie(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isPublic: Int,
    val isFriend: Int,
    val isFamily: Int,
    val dateFaved: String,
    val urlC: String?,
    val heightC: Int,
    val widthC: Int,
    val page: Int,
)

















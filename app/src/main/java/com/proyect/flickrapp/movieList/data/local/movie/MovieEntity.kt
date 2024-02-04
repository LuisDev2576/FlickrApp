package com.ahmedapps.moviesapp.movieList.data.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
@Entity
data class MovieEntity(

    @PrimaryKey
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isPublic: Int,
    val isFriend: Int,
    val isFamily: Int,
    val date_faved: String,
    val url_c: String,
    val height_c: Int,
    val width_c: Int,
)

















package com.proyect.flickrapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlickrEntity(

    @PrimaryKey
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,
    val date_faved: String,
    val url_c: String,
    val height_c: Int,
    val width_c: Int,

)

















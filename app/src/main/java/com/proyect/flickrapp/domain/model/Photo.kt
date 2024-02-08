package com.proyect.flickrapp.domain.model

data class Photo(
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
    val origen: String
)



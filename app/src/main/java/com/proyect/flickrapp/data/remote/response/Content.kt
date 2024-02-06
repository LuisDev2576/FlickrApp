package com.proyect.flickrapp.data.remote.response

data class Content(
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val total: Int,
    val photo: List<PhotoDto>,
)
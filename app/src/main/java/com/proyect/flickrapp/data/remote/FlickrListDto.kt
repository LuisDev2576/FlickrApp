package com.proyect.flickrapp.data.remote

data class FlickrListDto(
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val total: Int,
    //val photo: List<FlickrDto>?,
)
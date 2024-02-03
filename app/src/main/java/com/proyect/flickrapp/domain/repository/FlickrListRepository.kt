package com.proyect.flickrapp.domain.repository

import com.proyect.flickrapp.domain.model.Flickr
import com.proyect.flickrapp.util.Resource
import kotlinx.coroutines.flow.Flow


interface FlickrListRepository {

    suspend fun getFlickrList(
        forceFetchFromRemote: Boolean,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<Flickr>>>

}
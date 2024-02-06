package com.proyect.flickrapp.domain.repository

import com.proyect.flickrapp.domain.model.Photo
import com.proyect.flickrapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoListRepository {

    suspend fun getFavoritePhotosList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Photo>>>

    // TODO Intentar cambiar el resultado de List<Photo> por Content para poder utilizar la pagina actual, el numero de paginas y el total de photos disponibles
}
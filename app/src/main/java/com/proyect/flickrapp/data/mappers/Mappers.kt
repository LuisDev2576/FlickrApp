package com.proyect.flickrapp.data.mappers

import com.proyect.flickrapp.data.local.PhotoEntity
import com.proyect.flickrapp.data.remote.response.PhotoDto
import com.proyect.flickrapp.domain.model.Photo

fun PhotoDto.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
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

fun PhotoEntity.toPhoto(): Photo {
    return Photo(
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
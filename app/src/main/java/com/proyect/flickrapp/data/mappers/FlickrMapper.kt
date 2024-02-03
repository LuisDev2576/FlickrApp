 package com.proyect.flickrapp.data.mappers


import com.proyect.flickrapp.data.local.FlickrEntity
import com.proyect.flickrapp.data.remote.FlickrDto
import com.proyect.flickrapp.domain.model.Flickr

 fun FlickrDto.toFlickrEntity(): FlickrEntity {
    return FlickrEntity(
        id = id,
        owner = owner,
        secret = secret,
        server = server,
        farm = farm,
        title = title,
        ispublic = isPublic,
        isfamily = isFamily,
        isfriend = isFriend,
        date_faved = date_faved,
        url_c = url_c,
        height_c = height_c,
        width_c = width_c
    )
}

fun FlickrEntity.toFlickr(): Flickr {
    return Flickr(
        id = id,
        owner = owner,
        secret = secret,
        server = server,
        farm = farm,
        title = title,
        isPublic = ispublic,
        isFamily = isfamily,
        isFriend = isfriend,
        dateFaved = date_faved,
        urlC = url_c,
        heightC = height_c,
        widthC = width_c
    )
}





















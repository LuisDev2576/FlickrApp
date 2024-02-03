package com.proyect.flickrapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FlickrDao {

    @Upsert
    suspend fun upsertFlickrList(flickrList: List<FlickrEntity>)

}















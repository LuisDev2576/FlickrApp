package com.proyect.flickrapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PhotoDao {
    @Upsert
    suspend fun upsertPhotoList(photoList: List<PhotoEntity>)

    @Query("SELECT * FROM PhotoEntity")
    suspend fun getPopularPhotos(): List<PhotoEntity>
}

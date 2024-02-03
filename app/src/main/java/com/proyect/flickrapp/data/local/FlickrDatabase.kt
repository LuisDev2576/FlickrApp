package com.proyect.flickrapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FlickrEntity::class],
    version = 1
)
abstract class FlickrDatabase: RoomDatabase() {
    abstract val movieDao: FlickrDao
}
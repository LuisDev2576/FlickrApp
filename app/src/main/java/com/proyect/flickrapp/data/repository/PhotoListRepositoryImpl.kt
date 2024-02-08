package com.proyect.flickrapp.data.repository

import com.proyect.flickrapp.data.local.PhotosDatabase
import com.proyect.flickrapp.data.mappers.toPhoto
import com.proyect.flickrapp.data.mappers.toPhotoEntity
import com.proyect.flickrapp.data.remote.FlickrApi
import com.proyect.flickrapp.domain.model.Photo
import com.proyect.flickrapp.domain.repository.PhotoListRepository
import com.proyect.flickrapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PhotoListRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    private val photosDatabase: PhotosDatabase
) : PhotoListRepository {

    override suspend fun getPopularPhotosList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Photo>>> {
        return flow {

            emit(Resource.Loading(true))

            delay(5000)
            val localPhotoList = photosDatabase.photoDao.getPopularPhotos()

            val shouldLoadLocalPhoto = localPhotoList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalPhoto) {
                emit(Resource.Success(
                    data = localPhotoList.map { photoEntity ->
                        photoEntity.toPhoto(origen = "Local")
                    }.sortedWith(compareBy({ it.page }, { it.id }
                    ))
                ))

                emit(Resource.Loading(false))
                return@flow
            }

            val photoListFromApi = try {
                flickrApi.getPhotosList(page = page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading photos"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading photos"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading photos"))
                return@flow
            }

            val photoEntities = photoListFromApi.photos.photo.let {
                it.map { photoDto ->
                    photoDto.copy(page = photoListFromApi.photos.page).toPhotoEntity()
                }
            }

            photosDatabase.photoDao.upsertPhotoList(photoEntities)

            emit(Resource.Success(
                photoEntities.map { it.toPhoto(origen = "Remote") }.sortedWith(compareBy({ it.page }, { it.id }
                ))
            ))
            emit(Resource.Loading(false))

        }
    }

}

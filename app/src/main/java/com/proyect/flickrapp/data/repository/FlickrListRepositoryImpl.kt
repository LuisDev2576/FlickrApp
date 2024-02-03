package com.proyect.flickrapp.data.repository

import com.proyect.flickrapp.data.local.FlickrDatabase
import com.proyect.flickrapp.data.mappers.toFlickr
import com.proyect.flickrapp.data.mappers.toFlickrEntity
import com.proyect.flickrapp.data.remote.FlickrApi
import com.proyect.flickrapp.domain.model.Flickr
import com.proyect.flickrapp.domain.repository.FlickrListRepository
import com.proyect.flickrapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class FlickrListRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    private val flickrDatabase: FlickrDatabase
) : FlickrListRepository {

    override suspend fun getFlickrList(
        forceFetchFromRemote: Boolean,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<Flickr>>> {
        return flow {

            emit(Resource.Loading(true))

            val flickrListFromApi = try {
                flickrApi.getFlickrList(page = page, perPage = 10)
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

            val flickrEntities = flickrListFromApi.photo.let {
                it.map { movieDto ->
                    movieDto.toFlickrEntity()
                }
            }

            flickrDatabase.movieDao.upsertFlickrList(flickrEntities)

            emit(
                Resource.Success(
                    flickrEntities.map { it.toFlickr() }
                )
            )

            emit(Resource.Loading(false))

        }
    }
}




















package com.proyect.flickrapp.movieList.data.repository

import com.ahmedapps.moviesapp.movieList.data.mappers.toMovie
import com.ahmedapps.moviesapp.movieList.data.remote.MovieApi
import com.ahmedapps.moviesapp.movieList.domain.model.Movie
import com.ahmedapps.moviesapp.movieList.domain.repository.MovieListRepository
import com.ahmedapps.moviesapp.movieList.util.Category
import com.ahmedapps.moviesapp.movieList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
) : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading(true))


            val movieListFromApi = try {
                movieApi.getMoviesList(page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto
                }
            }


            emit(Resource.Success(
                movieEntities.map { it.toMovie() }
            ))
            emit(Resource.Loading(false))

        }
    }

}




















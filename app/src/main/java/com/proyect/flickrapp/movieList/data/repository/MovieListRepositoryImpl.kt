package com.ahmedapps.moviesapp.movieList.data.repository

import com.ahmedapps.moviesapp.movieList.data.local.movie.MovieDatabase
import com.ahmedapps.moviesapp.movieList.data.mappers.toMovie
import com.ahmedapps.moviesapp.movieList.data.mappers.toMovieEntity
import com.ahmedapps.moviesapp.movieList.data.remote.MovieApi
import com.ahmedapps.moviesapp.movieList.domain.model.Movie
import com.ahmedapps.moviesapp.movieList.domain.repository.MovieListRepository
import com.ahmedapps.moviesapp.movieList.util.Resource
import kotlinx.coroutines.delay
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
    private val movieDatabase: MovieDatabase
) : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading(true))

            delay(5000)
            val localMovieList = movieDatabase.movieDao.getMovieListByCategory()

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie()
                    }.sortedWith(compareBy({ it.page },
                        //{ it.id }
                    ))
                ))

                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMoviesList(page = page)
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

            val movieEntities = movieListFromApi.photos.photo.let {
                it.map { movieDto ->
                    movieDto.copy(page = movieListFromApi.photos.page).toMovieEntity()
                }
            }

            movieDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(
                movieEntities.map { it.toMovie() }.sortedWith(compareBy({ it.page },
                    //{ it.id }
                ))
            ))
            emit(Resource.Loading(false))

        }
    }

}




















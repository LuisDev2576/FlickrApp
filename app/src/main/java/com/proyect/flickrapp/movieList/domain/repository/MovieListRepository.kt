package com.ahmedapps.moviesapp.movieList.domain.repository

import com.ahmedapps.moviesapp.movieList.domain.model.Movie
import com.ahmedapps.moviesapp.movieList.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>>

}
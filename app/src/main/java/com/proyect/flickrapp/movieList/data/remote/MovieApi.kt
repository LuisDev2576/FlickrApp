package com.ahmedapps.moviesapp.movieList.data.remote

import com.ahmedapps.moviesapp.movieList.data.remote.respnod.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
interface MovieApi {

    @GET("services/rest/?method=flickr.favorites.getPublicList&api_key=40bd373bb6a19a078023b06af055d03c&user_id=66956608%40N06&extras=url_c")
    suspend fun getMoviesList(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallBack: Int = 1,
    ): MovieListDto

    companion object {
        const val BASE_URL = "https://www.flickr.com/"
    }

}
package com.proyect.flickrapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("services/rest/?method=flickr.favorites.getPublicList&api_key=40bd373bb6a19a078023b06af055d03c&user_id=66956608%40N06&extras=url_c")
    suspend fun getBeers(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int = 1,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallBack: Int = 1,
    ): FlickrListDto

    companion object {
        const val BASE_URL = "https://www.flickr.com/"
    }
}
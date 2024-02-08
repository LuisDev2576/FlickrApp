package com.proyect.flickrapp.data.remote

import com.proyect.flickrapp.data.remote.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("services/rest/?method=flickr.favorites.getPublicList&api_key=40bd373bb6a19a078023b06af055d03c&user_id=66956608%40N06&extras=url_c")
    suspend fun getPhotosList(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallBack: Int = 1,
    ): Response

    companion object {
        const val BASE_URL = "https://www.flickr.com/"
    }
    /*

    @GET("services/rest/?method=flickr.favorites.getPublicList")
    suspend fun getPhotosList(
        @Query("api_key") key: String = API_KEY,
        @Query("user_id") userId: String = "66956608%40N06",
        @Query("extras") extras: String = "url_c",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallBack: Int = 1,
    ): Response

    companion object {
        const val BASE_URL = "https://www.flickr.com/"
        const val API_KEY = "40bd373bb6a19a078023b06af055d03c"
    }
     */

}
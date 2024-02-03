package com.proyect.flickrapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FlickrApi {

    @GET("services/rest/")
    suspend fun getFlickrList(
        @Path("method") method: String = "flickr.favorites.getPublicList",
        @Query("api_key") apiKey: String = API_KEY,
        @Query("user_id") userId: String = "66956608%40N06",
        @Query("extras") extras: String = "url_c",
        @Query("per_page") perPage: Int,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallBack: Int = 1,
        @Query("page") page: Int,
    ): FlickrListDto

    companion object {
        const val BASE_URL = "https://www.flickr.com/"
        const val API_KEY = "xxxx"
    }

}
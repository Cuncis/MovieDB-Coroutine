package com.gdc.moviedbcoroutine.data.remote

import com.gdc.moviedbcoroutine.data.model.NowPlayingResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbApi {

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("language") language: String):
            Response<NowPlayingResponse>
}
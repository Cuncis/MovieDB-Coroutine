package com.gdc.moviedbcoroutine.data.remote

import com.gdc.moviedbcoroutine.data.model.NowPlayingDetailResponse
import com.gdc.moviedbcoroutine.data.model.NowPlayingResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("language") language: String):
            Response<NowPlayingResponse>

    @GET("3/movie/{movie_id}")
    suspend fun getNowPlayingDetail(@Path("movie_id") movieId: String,
                                    @Query("language") language: String):
            Response<NowPlayingDetailResponse>
}
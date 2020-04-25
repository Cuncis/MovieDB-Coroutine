package com.gdc.moviedbcoroutine.data.model

import com.google.gson.annotations.SerializedName




data class NowPlayingResponse(
    @SerializedName("dates")
    var dates: Dates? = null,

    @SerializedName("page")
    var page: Int = 0,

    @SerializedName("total_pages")
    var totalPages: Int = 0,

    @SerializedName("results")
    var results: List<NowPlaying>? = null,

    @SerializedName("total_results")
    var totalResults: Int = 0
)
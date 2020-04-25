package com.gdc.moviedbcoroutine.data.model

import com.google.gson.annotations.SerializedName




data class Dates(
    @SerializedName("maximum")
    private var maximum: String? = null,

    @SerializedName("minimum")
    private var minimum: String? = null
)
package com.gdc.moviedbcoroutine.util

import java.util.*

class Utility {
    companion object {

        fun getCountry(): String {
            val country = Locale.getDefault().country.toLowerCase()

            return if (country == "id") {
                country
            } else {
                "en"
            }
        }
    }
}
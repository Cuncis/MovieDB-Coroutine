package com.gdc.moviedbcoroutine.util

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gdc.moviedbcoroutine.R
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

        fun showLog(message: String) {
            Log.d("_logMovie", message)
        }

        fun ImageView.loadImage(uri: String?) {
            val options = RequestOptions()
                .error(R.mipmap.ic_launcher_round)
            Glide.with(this.context)
                .setDefaultRequestOptions(options)
                .load(uri)
                .into(this)
        }
    }
}
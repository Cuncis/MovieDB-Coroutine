package com.gdc.moviedbcoroutine.ui.upcoming

import androidx.lifecycle.MutableLiveData
import com.gdc.moviedbcoroutine.data.model.Upcoming
import com.gdc.moviedbcoroutine.data.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UpcomingRepository {

    private val upcomingList = MutableLiveData<List<Upcoming>>()
    private val loadError = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    fun fetchUpcomingMovie(language: String): MutableLiveData<List<Upcoming>> {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.theMovieDbApi.getUpcomingMovie(language)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        loading.value = false
                        upcomingList.postValue(response.body()?.results)
                        loadError.postValue(null)
                    } else {
                        onError(response.message())
                    }
                } catch (e: Exception) {
                    loading.value = false
                    e.printStackTrace()
                }
            }
        }
        return upcomingList
    }

    fun getMessageError(): MutableLiveData<String> {
        return loadError
    }

    fun onLoading(): MutableLiveData<Boolean> {
        return loading
    }

    private fun onError(message: String) {
        loading.value = false
        loadError.postValue(message)
    }

}
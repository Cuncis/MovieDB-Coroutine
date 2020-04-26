package com.gdc.moviedbcoroutine

import androidx.lifecycle.MutableLiveData
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.data.remote.ApiClient
import kotlinx.coroutines.*

class MainRepository {

    var job: Job? = null
    val exeptionHandler = CoroutineExceptionHandler {coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    private val nowPlayingList = MutableLiveData<List<NowPlaying>>()
    private var loadError = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()


    fun fetchNowPlaying(): MutableLiveData<List<NowPlaying>> {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exeptionHandler).launch {
            val response = ApiClient.theMovieDbApi.getNowPlayingMovie("in")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                    nowPlayingList.value = response.body()?.results
                    loadError.value = null
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }

        return nowPlayingList
    }

    fun getMessageError(): MutableLiveData<String> {
        return loadError
    }

    fun onLoading(): MutableLiveData<Boolean> {
        return loading
    }

    fun clear() {
        job?.cancel()
    }

    private fun onError(message: String) {
        loading.value = false
        loadError.value = message
    }

}
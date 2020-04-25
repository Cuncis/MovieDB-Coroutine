package com.gdc.moviedbcoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.data.model.NowPlayingResponse
import com.gdc.moviedbcoroutine.data.remote.ApiClient
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    var job: Job? = null
//    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        onError("Exception: ${throwable.localizedMessage}")
//    }

    val nowPlayingList = MutableLiveData<NowPlayingResponse>()
//    val nowPlayingLoadError = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchNowPlaying()
    }

    private fun fetchNowPlaying() {
        loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.theMovieDbApi.getNowPlayingMovie(BuildConfig.API_KEY, "in")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                    nowPlayingList.value = response.body()
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        loading.value = false
//        nowPlayingLoadError.value = message
//    }

//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }
    }
}
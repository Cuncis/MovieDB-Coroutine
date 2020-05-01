package com.gdc.moviedbcoroutine.ui.now_playing

import androidx.lifecycle.MutableLiveData
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.data.remote.ApiClient
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.coroutines.*
import java.io.IOException

class NowPlayingRepository {

    var job: Job? = null
    private val exeptionHandler = CoroutineExceptionHandler { _, throwable ->
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
                try {
                    if (response.isSuccessful) {
                        loading.value = false
                        nowPlayingList.postValue(response.body()?.results)
                        loadError.postValue(null)
                    } else {
                        onError("Error: ${response.message()}")
                    }
                } catch (e: CancellationException) {
                    Utility.showLog("Error: ${e.message}")
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
        loadError.postValue(message)
    }

}
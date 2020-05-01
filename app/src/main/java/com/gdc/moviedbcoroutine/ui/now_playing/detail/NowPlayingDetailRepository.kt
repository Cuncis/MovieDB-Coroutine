package com.gdc.moviedbcoroutine.ui.now_playing.detail

import androidx.lifecycle.MutableLiveData
import com.gdc.moviedbcoroutine.data.model.NowPlayingDetailResponse
import com.gdc.moviedbcoroutine.data.remote.ApiClient
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.coroutines.*
import java.lang.Exception

class NowPlayingDetailRepository {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    private val detailData = MutableLiveData<NowPlayingDetailResponse>()
    private val loadError = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    fun getNowPlayingDetail(movieId: String, language: String): MutableLiveData<NowPlayingDetailResponse> {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = ApiClient.theMovieDbApi.getNowPlayingDetail(movieId, language)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        loading.value = false
                        detailData.postValue(response.body())
                        loadError.postValue(null)
                    } else {
                        onError("Error: ${response.message()}")
                    }
                } catch (e: CancellationException) {
                    loading.value = false
                    Utility.showLog("Error: ${e.message}")
                }
            }
        }

        return detailData
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
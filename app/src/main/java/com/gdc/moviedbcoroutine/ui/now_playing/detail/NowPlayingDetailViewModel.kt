package com.gdc.moviedbcoroutine.ui.now_playing.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdc.moviedbcoroutine.data.model.NowPlayingDetailResponse

class NowPlayingDetailViewModel: ViewModel() {

    private val repository = NowPlayingDetailRepository()

    fun getNowPlayingDetail(movieId: Int, language: String): MutableLiveData<NowPlayingDetailResponse> {
        return repository.getNowPlayingDetail(movieId.toString(), language)
    }

    fun getLoadError(): MutableLiveData<String> {
        return repository.getMessageError()
    }

    fun onLoading(): MutableLiveData<Boolean> {
        return repository.onLoading()
    }

    override fun onCleared() {
        repository.clear()
    }
}
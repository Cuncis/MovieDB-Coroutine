package com.gdc.moviedbcoroutine.ui.now_playing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.ui.now_playing.NowPlayingRepository

class NowPlayingViewModel: ViewModel() {

    private val repository =
        NowPlayingRepository()

    fun getNowPlayingMovieList(): LiveData<List<NowPlaying>> {
        return repository.fetchNowPlaying()
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
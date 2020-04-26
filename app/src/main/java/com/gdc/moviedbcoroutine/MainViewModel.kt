package com.gdc.moviedbcoroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    private val repository = MainRepository()

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
        super.onCleared()
        repository.clear()
    }
}
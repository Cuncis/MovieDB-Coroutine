package com.gdc.moviedbcoroutine.ui.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdc.moviedbcoroutine.data.model.Upcoming

class UpcomingViewModel: ViewModel() {

    private val repository = UpcomingRepository()

    fun getUpcomingMovies(language: String): MutableLiveData<List<Upcoming>> {
        return repository.fetchUpcomingMovie(language)
    }

    fun getErrorMessage(): MutableLiveData<String> {
        return repository.getMessageError()
    }

    fun onLoading(): MutableLiveData<Boolean> {
        return repository.onLoading()
    }

}
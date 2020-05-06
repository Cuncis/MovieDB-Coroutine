package com.gdc.moviedbcoroutine.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gdc.moviedbcoroutine.data.local.MovieDatabase
import com.gdc.moviedbcoroutine.data.model.FavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val repository:FavoriteRepository

    init {
        val favoriteDao = MovieDatabase.getDatabase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
    }

    fun getAllFavorites(): LiveData<List<FavoriteModel>> {
        return repository.getAllFavorites()
    }

    fun addFavorite(favorite: FavoriteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.addFavorite(favorite)
    }

    fun removeFavorite(favorite: FavoriteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.removeFavorite(favorite)
    }

    fun getFlagByTitle(title: String): LiveData<FavoriteModel> {
        return repository.getFlagByTitle(title)
    }

}
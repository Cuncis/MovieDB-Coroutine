package com.gdc.moviedbcoroutine.ui.favorite

import androidx.lifecycle.LiveData
import com.gdc.moviedbcoroutine.data.model.FavoriteModel


class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    suspend fun addFavorite(favorite: FavoriteModel) {
        return favoriteDao.addFavorite(favorite)
    }

    fun getAllFavorites(): LiveData<List<FavoriteModel>> {
        return favoriteDao.getFavorites()
    }

    fun getFlagByTitle(title: String): LiveData<FavoriteModel> {
        return favoriteDao.getFlagByTitle(title)
    }

    suspend fun removeFavorite(favorite: FavoriteModel) {
        return favoriteDao.removeFavorite(favorite)
    }

}
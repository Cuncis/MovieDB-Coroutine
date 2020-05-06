package com.gdc.moviedbcoroutine.ui.favorite

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdc.moviedbcoroutine.data.model.FavoriteModel


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteModel)

    @Query("SELECT * FROM favorite_table")
    fun getFavorites(): LiveData<List<FavoriteModel>>

    @Query("SELECT * FROM favorite_table WHERE title LIKE :title")
    fun getFlagByTitle(title: String): LiveData<FavoriteModel>

    @Delete
    suspend fun removeFavorite(favorite: FavoriteModel)

}
package com.gdc.moviedbcoroutine.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gdc.moviedbcoroutine.data.model.FavoriteModel
import com.gdc.moviedbcoroutine.ui.favorite.FavoriteDao


@Database(entities = [FavoriteModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_db.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}
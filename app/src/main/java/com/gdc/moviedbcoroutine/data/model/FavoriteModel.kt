package com.gdc.moviedbcoroutine.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteModel(

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "category")
    var category: String = "",

    @ColumnInfo(name = "flag")
    var flag: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
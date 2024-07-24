package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvShow: List<TvShow>)

    @androidx.room.Query("DELETE FROM  popular_tvShows")
    suspend fun deleteAllTvShows()

    @androidx.room.Query("SELECT * from popular_tvShows")
    suspend fun getTvShows():List<TvShow>

}
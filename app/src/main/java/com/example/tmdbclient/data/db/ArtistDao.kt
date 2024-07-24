package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.tmdbclient.data.model.artist.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @androidx.room.Query("DELETE FROM  popular_artists")
    suspend fun deleteAllArtists()

    @androidx.room.Query("SELECT * from popular_artists")
    suspend fun getArtists():List<Artist>
}
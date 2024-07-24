package com.example.tmdbclient.data.repository.tvShow.dataSource

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.tvshow.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB():List<TvShow>
    suspend fun saveTvShowsToDB(artist:List<TvShow>)
    suspend fun clearAll()
}
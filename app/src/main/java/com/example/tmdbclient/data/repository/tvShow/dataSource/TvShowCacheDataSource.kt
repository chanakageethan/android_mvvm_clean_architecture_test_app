package com.example.tmdbclient.data.repository.tvShow.dataSource

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.tvshow.TvShow

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache():List<TvShow>
    suspend fun saveTvShowsToCache(artists:List<TvShow>)

}
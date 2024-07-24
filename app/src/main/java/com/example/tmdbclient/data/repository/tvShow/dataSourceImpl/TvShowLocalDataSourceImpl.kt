package com.example.tmdbclient.data.repository.tvShow.dataSourceImpl

import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.db.TvShowDao
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.tvShow.dataSource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvShowtDao: TvShowDao) : TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> =tvShowtDao.getTvShows()

    override suspend fun saveTvShowsToDB(tvShow: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowtDao.saveTvShows(tvShow)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowtDao.deleteAllTvShows()
        }
    }
}
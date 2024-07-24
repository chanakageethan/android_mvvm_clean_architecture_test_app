package com.example.tmdbclient.data.repository.artist.dataSourceImpl

import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao) : ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): List<Artist> =artistDao.getArtists()

    override suspend fun saveArtistsToDB(artist: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.saveArtists(artist)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }
}
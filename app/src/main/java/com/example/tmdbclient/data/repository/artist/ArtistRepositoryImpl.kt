package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository

class ArtistRepositoryImpl(
    private val artistsRemoteDataSource: ArtistRemoteDataSource,
    private val artistsLocalDataSource: ArtistLocalDataSource,
    private val artistsCacheDataSource: ArtistCacheDataSource
    ) : ArtistRepository {

    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        var newListOfArtist: List<Artist> = getArtistsFromAPI()
        artistsLocalDataSource.clearAll()
        artistsLocalDataSource.saveArtistsToDB(newListOfArtist)
        artistsCacheDataSource.saveArtistsToCache(newListOfArtist)
        return newListOfArtist
    }

    suspend fun getArtistsFromAPI():List<Artist>{
        lateinit var artistList:List<Artist>
        try{
            val response = artistsRemoteDataSource.getArtists()
            val body : ArtistList? = response.body()

            if(body != null){
                artistList = body.artists
            }

        }catch(exception :Exception){
            Log.i("MYTAG",exception.toString())
        }
        return artistList
    }

    suspend fun getArtistsFromDB():List<Artist>{
        lateinit var artistList:List<Artist>
        try{
            artistList = artistsLocalDataSource.getArtistsFromDB()
        }catch(exception :Exception){
            Log.i("MYTAG",exception.toString())
        }
        if (artistList.size > 0){
            return artistList
        }else{
            artistList = getArtistsFromAPI()
            artistsLocalDataSource.saveArtistsToDB(artistList)
        }
        return artistList
    }

    suspend fun getArtistsFromCache():List<Artist>{
        lateinit var artistList:List<Artist>
        try{
            artistList = artistsCacheDataSource.getArtistsFromCache()
        }catch(exception :Exception){
            Log.i("MYTAG",exception.toString())
        }
        if (artistList.size > 0){
            return artistList
        }else{
            artistList = getArtistsFromDB()
            artistsCacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }


}
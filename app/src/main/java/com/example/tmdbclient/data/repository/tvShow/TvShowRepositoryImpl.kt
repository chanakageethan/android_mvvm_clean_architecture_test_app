package com.example.tmdbclient.data.repository.tvShow

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.model.tvshow.TvShowList
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.tvShow.dataSource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvShow.dataSource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvShow.dataSource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowsRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
    ) : TvShowsRepository {

    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        var newListOfTvShows: List<TvShow> = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShows)
        return newListOfTvShows
    }

    suspend fun getTvShowsFromAPI():List<TvShow>{
        lateinit var tvShowsList:List<TvShow>
        try{
            val response = tvShowRemoteDataSource.getTvShows()
            val body : TvShowList? = response.body()

            if(body != null){
                tvShowsList = body.tvShows
            }

        }catch(exception :Exception){
            Log.i("MYTAG",exception.toString())
        }
        return tvShowsList
    }

    suspend fun getTvShowsFromDB():List<TvShow>{
        lateinit var tvShowsList:List<TvShow>
        try{
            tvShowsList = tvShowLocalDataSource.getTvShowsFromDB()
        }catch(exception :Exception){
            Log.i("MYTAG",exception.toString())
        }
        if (tvShowsList.size > 0){
            return tvShowsList
        }else{
            tvShowsList = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowsList)
        }
        return tvShowsList
    }

    suspend fun getTvShowsFromCache():List<TvShow>{
        lateinit var tvShowList:List<TvShow>
        try{
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        }catch(exception :Exception){
            Log.i("MYTAG",exception.toString())
        }
        if (tvShowList.size > 0){
            return tvShowList
        }else{
            tvShowList = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }
        return tvShowList
    }


}
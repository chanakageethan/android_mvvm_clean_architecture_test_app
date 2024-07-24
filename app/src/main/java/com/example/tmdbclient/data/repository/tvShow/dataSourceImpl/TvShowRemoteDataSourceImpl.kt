package com.example.tmdbclient.data.repository.tvShow.dataSourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.model.tvshow.TvShowList
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.movie.dataSource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.tvShow.dataSource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey: String) :
    TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TvShowList> = tmdbService.getPopularTvShows(apiKey)

}
package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.BuildConfig
import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.repository.artist.dataSource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.artist.dataSourceImpl.ArtistRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.movie.dataSource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.movie.dataSourceIml.MovieRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.tvShow.dataSource.TvShowRemoteDataSource
import com.example.tmdbclient.data.repository.tvShow.dataSourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule(

) {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource( tmdbService: TMDBService):MovieRemoteDataSource{
            return MovieRemoteDataSourceImpl(
                tmdbService, BuildConfig.API_KEY
            )
    }

    @Singleton
    @Provides
    fun provideTvRemoteDataSource( tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(
            tmdbService,BuildConfig.API_KEY
        )
    }

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource( tmdbService: TMDBService): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(
            tmdbService,BuildConfig.API_KEY
        )
    }
}
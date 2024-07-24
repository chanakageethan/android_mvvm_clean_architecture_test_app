package com.example.tmdbclient.data.repository.artist.dataSource

import com.example.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
}
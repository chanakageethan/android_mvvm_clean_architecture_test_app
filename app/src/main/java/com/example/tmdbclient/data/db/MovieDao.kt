package com.example.tmdbclient.data.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.tmdbclient.data.model.movie.Movie


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movie: List<Movie>)

    @androidx.room.Query("DELETE FROM  popular_movies")
    suspend fun deleteAllMovies()

    @androidx.room.Query("SELECT * from popular_movies")
    suspend fun getMovies():List<Movie>

}
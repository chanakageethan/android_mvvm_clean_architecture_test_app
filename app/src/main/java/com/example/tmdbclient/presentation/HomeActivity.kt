package com.example.tmdbclient.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.databinding.ActivityMainBinding
import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.movie.MovieActivity
import com.example.tmdbclient.presentation.tv.TvShowActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.movieButton.setOnClickListener(){
            val intent =  Intent(this,MovieActivity::class.java)
            startActivity(intent)
        }
        binding.tvButton.setOnClickListener(){
            val intent =  Intent(this,TvShowActivity::class.java)
            startActivity(intent)
        }
        binding.artistsButton.setOnClickListener(){
            val intent =  Intent(this,ArtistActivity::class.java)
            startActivity(intent)
        }
    }
}
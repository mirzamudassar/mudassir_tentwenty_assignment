package com.example.moviesapi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviesapi.adapter.UpcomingMovieListAdapter
import com.example.moviesapi.databinding.ActivityMovieDetailBinding
import com.example.moviesapi.model.Geners
import com.example.moviesapi.model.MovieDetailModel
import com.example.moviesapi.model.ResultUpcomingMovies
import com.example.moviesapi.model.UpcomingMoviesModel
import com.example.moviesapi.repository.Repository
import com.example.moviesapi.viewModel.MainViewModel
import com.example.moviesapi.viewModel.MainViewModelFactory

class MovieDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieDetailBinding


    private lateinit var viewModel: MainViewModel

    var allGenres = ""
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id" , 0)
        Log.e("Message" , ""+id)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        if (id != null) {
            viewModel.getMovieDetail( id,"b318fc9422d96c7df24b170a16ed0527" )
        }
        viewModel.getMovieDetailResponse.observe(this, Observer{ response ->
            if (response.isSuccessful) {
                Log.e("Message", "Sucess " + response.body())
                updateUi(response.body())
            }
            else
            {
                Toast.makeText(
                    this,
                    "Error is " + response.code(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Message" , "Error " + response.message())
            }
        })


    }

    private fun updateUi(body: MovieDetailModel?) {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+body?.poster_path)
            .override(300, 200)
            .into(binding.image);
        binding.dateTv.setText(body?.release_date)
        binding.titleTv.setText(body?.title)
        binding.overviewTv.setText(body?.overview)
        var genres : ArrayList<Geners>? = body?.genres
        Log.e("Message" ,"Genres Are"+genres)

        if (genres != null) {
            position = genres.size
            for (g in genres)
            {
                val genresToStore : String = g.name

                var gener : Geners = genres.get(position-1)

                if (g.equals(gener))
                {
                    allGenres = allGenres+genresToStore+""
                    Log.e("Message" , genresToStore)
                    binding.genresTv.setText(allGenres)
                }

                else
                {
                    allGenres = allGenres+genresToStore+", "
                    Log.e("Message" , genresToStore)
                    binding.genresTv.setText(allGenres)
                }



            }
        }

    }
}
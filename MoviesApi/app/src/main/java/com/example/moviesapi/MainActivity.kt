package com.example.moviesapi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapi.adapter.UpcomingMovieListAdapter
import com.example.moviesapi.databinding.ActivityMainBinding
import com.example.moviesapi.model.ResultUpcomingMovies
import com.example.moviesapi.model.UpcomingMoviesModel
import com.example.moviesapi.repository.Repository
import com.example.moviesapi.viewModel.MainViewModel
import com.example.moviesapi.viewModel.MainViewModelFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

     lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.upcomingMoviewRv?.layoutManager = LinearLayoutManager(this)
        binding.upcomingMoviewRv?.setHasFixedSize(true)


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getUpcomingMovies("b318fc9422d96c7df24b170a16ed0527")

        viewModel.getUpcomingMoviesResponse.observe(this, Observer{ response ->
            if (response.isSuccessful) {
                val result : ArrayList<ResultUpcomingMovies> = response.body()?.results!!

                Log.e("Message", "Sucess " + result)

                val upcomingMovies : UpcomingMoviesModel? = response.body()
                val upcomingMovieList : ArrayList<ResultUpcomingMovies>? = upcomingMovies?.results

                val adapter = UpcomingMovieListAdapter(upcomingMovieList!!, this)
                binding.upcomingMoviewRv.setAdapter(adapter)

                adapter.setOnitemClickListener(object :
                    UpcomingMovieListAdapter.onItemClicklistener {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onclick(position : Int) {
                        Log.e("Message", "You clicked" + position)
                        val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                        intent.putExtra("id", upcomingMovieList.get(position).id)
                        Log.e("Message" ,"Record "+upcomingMovieList.get(position)+" Size is" +upcomingMovieList.size)
                        startActivity(intent)
                    }
                })



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
}
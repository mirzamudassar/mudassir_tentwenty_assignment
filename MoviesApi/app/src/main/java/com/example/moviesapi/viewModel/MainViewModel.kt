package com.example.moviesapi.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.model.MovieDetailModel
import com.example.moviesapi.model.UpcomingMoviesModel
import com.example.moviesapi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository) : ViewModel() {


    var getUpcomingMoviesResponse: MutableLiveData<Response<UpcomingMoviesModel>> = MutableLiveData()
    var getMovieDetailResponse: MutableLiveData<Response<MovieDetailModel>> = MutableLiveData()


    fun getUpcomingMovies(apikey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getUpcomingMovies(apikey)
                getUpcomingMoviesResponse.value = response

            } catch (e: Exception) {
                Log.e("Message", "Exception is " +e)
            }
        }
    }

    fun getMovieDetail(id: Int ,apikey: String ) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieDetail(apikey , id)
                getMovieDetailResponse.value = response

            } catch (e: Exception) {
                Log.e("Message", "Exception is " +e)
            }
        }
    }
}
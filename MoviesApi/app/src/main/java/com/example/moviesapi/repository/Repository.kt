package com.example.moviesapi.repository

import com.example.moviesapi.api.ApiClient
import com.example.moviesapi.api.ApiInterface
import com.example.moviesapi.model.MovieDetailModel
import com.example.moviesapi.model.UpcomingMoviesModel
import retrofit2.Response

class Repository {
    val apiInterface: ApiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)

    suspend fun getUpcomingMovies(apiKey:String): Response<UpcomingMoviesModel> {
        return apiInterface.getUpcomingMovies(apiKey)
    }

    suspend fun getMovieDetail(apiKey:String , id : Int): Response<MovieDetailModel> {
        return apiInterface.getMovieDetail(  id ,apiKey)
    }

}
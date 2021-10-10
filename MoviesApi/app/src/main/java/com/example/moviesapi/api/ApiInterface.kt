package com.example.moviesapi.api

import com.example.moviesapi.model.MovieDetailModel
import com.example.moviesapi.model.UpcomingMoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key: String):Response<UpcomingMoviesModel>

    @GET("{id}")
    suspend fun getMovieDetail(
        @Path("id") movie_id: Int,
        @Query("api_key") api_key: String
        ):Response<MovieDetailModel>

}
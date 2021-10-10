package com.example.moviesapi.model

data class UpcomingMoviesModel(
    var dates : Dates,
    var pages : Int,
    var results: ArrayList<ResultUpcomingMovies>,
    var total_pages : String,
    var total_results : Int
)
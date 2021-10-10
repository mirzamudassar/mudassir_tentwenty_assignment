package com.example.moviesapi.model

data class MovieDetailModel (
    var adult : Boolean,
    var backdrop_path : String,
    var belongs_to_collection : Any,
    var budget : Int,
    var genres : ArrayList<Geners>,
    var homepage : String,
    var id : Int ,
    var imdb_id : String,
    var original_language : String,
    var original_title : String,
    var overview : String ,
    var popularity: Double,
    var poster_path : String,
    var production_companies : ArrayList<Any>,
    var production_countries : ArrayList<Any>,
    var release_date : String,
    var revenue : Int,
    var runtime : Int,
    var spoken_languages : ArrayList<Any>,
    var status : String,
    var tagline : String,
    var title : String,
    var video : Boolean ,
    var vote_average : Double,
    var vote_count : Int

    )
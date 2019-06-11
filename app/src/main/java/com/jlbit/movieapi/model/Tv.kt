package com.jlbit.movieapi.model

data class Tv(
    val poster_path: String?,
    val popularity: Float,
    val id: Int,
    val backdrop_path: String?,
    val vote_average: Float,
    val overview: String,
    val first_air_date: String,
    val origin_country: ArrayList<String>,
    val genre_ids: ArrayList<Int>,
    val original_language: String,
    val vote_count: Int,
    val name: String,
    val original_name: String
)
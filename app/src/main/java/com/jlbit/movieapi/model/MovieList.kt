package com.jlbit.movieapi.model

data class MovieList(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val dates: Dates = Dates("",""),
    val results: ArrayList<Movie>
)
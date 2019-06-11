package com.jlbit.movieapi.model

data class TvList(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: ArrayList<Tv>
)
package com.jlbit.movieapi.model

data class MovieDetail(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: BelongsCollection,
    val budget: Int,
    val genre_ids: ArrayList<Genre>,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String?,
    val production_companies: ArrayList<ProductionCompanie>,
    val production_countries: ArrayList<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int?,
    val spoken_languages: ArrayList<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)
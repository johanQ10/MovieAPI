package com.jlbit.movieapi.model

data class Filter(
    val api_key: String,
    val sort_by: String,
    val include_adult: Boolean,
    val include_video: Boolean,
    val with_genres: String,
    val year: Int?,
    val with_original_language: String
)
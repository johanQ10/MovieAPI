package com.jlbit.movieapi.model

data class CreatedBy(
    val id: Int,
    val credit_id: String,
    val name: String,
    val gender: Int,
    val profile_path: String
)
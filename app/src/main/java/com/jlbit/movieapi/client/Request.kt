package com.jlbit.movieapi.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Request {
    private val urlApi = "https://api.themoviedb.org/3/"
    val apiKey = "b0e267af9dd87ba3d77f6a01ea4a4c38"
    val youtubeApiKey = "AIzaSyD1iTpPG0lPDqwwxDoX0GINNO0ZVj4Sad4"
    val urlImage = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/"

    fun retrofit(): API {
        val retrofit = Retrofit.Builder()
            .baseUrl(urlApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(API::class.java)
    }
}
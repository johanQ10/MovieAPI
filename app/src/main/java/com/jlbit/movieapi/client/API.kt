package com.jlbit.movieapi.client

import com.jlbit.movieapi.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    //MOVIES

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Call<MovieList>

    @GET("movie/top_rated")
    fun getMovieTopRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Call<MovieList>

    @GET("movie/upcoming")
    fun getMovieUpcoming(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Call<MovieList>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String
    ): Call<MovieDetail>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<Videos>

    @GET("genre/movie/list")
    fun getMovieGenres(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<Genres>

    @GET("discover/movie")
    fun getDiscoverMovies(
        @Query("api_key") api_key: String,
        @Query("sort_by") sort_by: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("page") page: Int,
        @Query("with_genres") with_genres: String,
        @Query("year") year: Int?,
        @Query("with_original_language") with_original_language: String
    ): Call<MovieList>

    //TV

    @GET("tv/popular")
    fun getTvPopular(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TvList>

    @GET("tv/top_rated")
    fun getTvTopRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TvList>

    @GET("tv/on_the_air")
    fun getTvUpcoming(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TvList>

    @GET("tv/{tv_id}")
    fun getTvDetail(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String
    ): Call<TvDetail>

    @GET("tv/{tv_id}/videos")
    fun getTvVideos(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<Videos>

    @GET("discover/tv")
    fun getDiscoverTv(
        @Query("api_key") api_key: String,
        @Query("sort_by") sort_by: String,
        @Query("first_air_date_year") first_air_date_year: Int?,
        @Query("page") page: Int,
        @Query("with_genres") with_genres: String,
        @Query("with_original_language") with_original_language: String
    ): Call<TvList>
}
package com.example.minktestapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "ada2680af1999ffe46e6b1916562c3b7",
        @Query("page") page: Int,
        @Query("language") language: String = "fr-FR"
    ): Call<GetMoviesResponse>

    @GET("search/movie")
    fun getSearchMovie(
        @Query("api_key") apiKey: String = "ada2680af1999ffe46e6b1916562c3b7",
        @Query("page") page: Int,
        @Query("language") language: String = "fr-FR",
        @Query("query") query: String
    ): Call<GetMoviesResponse>
}
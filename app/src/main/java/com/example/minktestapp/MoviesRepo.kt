package com.example.minktestapp

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepo {

    private val api: API
    private val url = "https://api.themoviedb.org/3/"

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()

        api = retrofit.create(API::class.java)
    }

    fun getPopularMovies(page: Int = 1,
                         onSuccess: (movies: List<Movie>) -> Unit,
                         onError: () -> Unit) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }

    fun getSearchMovies(page: Int = 1, search: String = "",
                         onSuccess: (movies: List<Movie>) -> Unit,
                         onError: () -> Unit) {
        api.getSearchMovie(page = page, query = search)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }
}
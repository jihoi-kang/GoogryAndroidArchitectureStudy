package com.example.myproject.data.service

import com.example.myproject.data.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {
    @Headers(
        "X-Naver-Client-Id: $NAVER_CLIENT_ID",
        "X-Naver-Client-Secret: $NAVER_CLIENT_SECRET"
    )
    @GET("movie.json")
    fun getMovies(
        @Query("query") title: String
    ): Call<Movie>

}
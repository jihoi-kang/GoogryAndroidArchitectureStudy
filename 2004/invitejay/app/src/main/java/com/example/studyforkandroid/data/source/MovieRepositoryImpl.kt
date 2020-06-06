package com.example.studyforkandroid.data.source

import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.data.source.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getRemoteMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        movieRemoteDataSource.getMovieList(query, onSuccess, onFailure)
    }
}
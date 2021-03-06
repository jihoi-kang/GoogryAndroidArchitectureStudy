package com.jay.data.room

import androidx.room.*
import com.jay.data.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun getMovies(): List<Movie>

    @Query("DELETE FROM Movie")
    fun clearMovies()

}
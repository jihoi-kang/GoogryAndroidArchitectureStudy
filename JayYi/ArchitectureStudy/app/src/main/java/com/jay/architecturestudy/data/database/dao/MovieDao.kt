package com.jay.architecturestudy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.MovieEntity
import io.reactivex.Single

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * from movie")
    fun getAll(): Single<List<MovieEntity>>

    @Query("DELETE from movie")
    fun clearAll()
}
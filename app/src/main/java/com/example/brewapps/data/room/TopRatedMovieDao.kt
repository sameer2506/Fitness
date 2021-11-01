package com.example.brewapps.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.brewapps.data.room.MovieRoom

@Dao
interface TopRatedMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedMovieList(nowPlayingMovieRoom: List<MovieRoom>)

    @Query("SELECT * FROM movieRoom")
    fun getTopRatedMovieList() : List<MovieRoom>
}
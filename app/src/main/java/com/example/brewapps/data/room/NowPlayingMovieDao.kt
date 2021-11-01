package com.example.brewapps.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.brewapps.data.room.MovieRoom

@Dao
interface NowPlayingMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNowPlayingMovieList(nowPlayingMovieRoom: List<MovieRoom>)

    @Query("SELECT * FROM movieRoom")
    fun getNowPlayingMovieList() : List<MovieRoom>
}
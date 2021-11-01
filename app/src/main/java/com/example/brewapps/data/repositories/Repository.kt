package com.example.brewapps.data.repositories

import com.example.brewapps.data.network.Api
import com.example.brewapps.data.network.SafeApiRequest
import com.example.brewapps.data.room.AppDatabase
import com.example.brewapps.data.room.MovieRoom

class Repository(
    private val api: Api,
    private val db: AppDatabase
) : SafeApiRequest() {

    // Now playing movie list
    suspend fun nowPlayingMovieList() = safeApiRequest {
        api.getNowPlayingMovieList()
    }

    // Now playing movie list
    suspend fun topRatedMovieList() = safeApiRequest {
        api.getTopRatedMovieList()
    }

    suspend fun saveNowPlayingMovieList(data: List<MovieRoom>) =
        db.getNowPlMovList().addNowPlayingMovieList(data)

    fun getNowPlayingMovieList() = db.getNowPlMovList().getNowPlayingMovieList()

    fun getSearchNowPlayMovList(name: String) =
        db.getNowPlMovList().getSearchNowPlayMovList(name)

    suspend fun saveTopRatedMovieList(data: List<MovieRoom>) =
        db.getTopRatedMovList().addTopRatedMovieList(data)

    fun getTopRatedMovieList() = db.getTopRatedMovList().getTopRatedMovieList()

    fun getSearchTopRatedMovList(name: String) =
        db.getTopRatedMovList().getSearchTopRatedMovList(name)

}
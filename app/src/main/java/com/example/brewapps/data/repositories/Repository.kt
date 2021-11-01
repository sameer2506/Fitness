package com.example.brewapps.data.repositories

import com.example.brewapps.data.network.Api
import com.example.brewapps.data.network.SafeApiRequest

class Repository(
    private val api: Api
) : SafeApiRequest() {

    // Now playing movie list
    suspend fun nowPlayingMovieList() = safeApiRequest {
        api.getNowPlayingMovieList()
    }

    // Now playing movie list
    suspend fun topRatedMovieList() = safeApiRequest {
        api.getTopRatedMovieList()
    }
}
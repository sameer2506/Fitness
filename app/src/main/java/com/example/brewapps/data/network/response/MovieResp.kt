package com.example.brewapps.data.network.response

import com.example.brewapps.data.entities.Dates
import com.example.brewapps.data.room.MovieRoom

data class MovieResp(
    val dates: Dates,
    val page: Int,
    val results: List<MovieRoom>,
    val total_pages: Int,
    val total_results: Int
)
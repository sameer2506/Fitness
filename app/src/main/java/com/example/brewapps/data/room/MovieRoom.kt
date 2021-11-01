package com.example.brewapps.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieRoom(
    val adult: Boolean,
    val backdrop_path: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)
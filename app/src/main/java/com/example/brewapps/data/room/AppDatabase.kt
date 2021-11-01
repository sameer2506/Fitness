package com.example.brewapps.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MovieRoom::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNowPlMovList(): NowPlayingMovieDao
    abstract fun getTopRatedMovList() : TopRatedMovieDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "movie"
        ).build()

    }
}
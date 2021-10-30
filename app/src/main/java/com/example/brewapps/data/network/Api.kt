package com.example.brewapps.data.network

import com.example.brewapps.data.entities.nowPlaying.NowPlayingData
import com.example.brewapps.security.API_KEY
import com.example.brewapps.security.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface Api {

    @GET("now_playing?api_key=${API_KEY}")
    suspend fun getNowPlayingMovieList() : NowPlayingData

    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : Api {
            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(networkConnectionInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}
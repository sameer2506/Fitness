package com.example.brewapps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewapps.data.network.response.MovieResp
import com.example.brewapps.data.network.Resource
import com.example.brewapps.data.repositories.Repository
import com.example.brewapps.data.room.MovieRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ViewModelClass(private val repository: Repository) : ViewModel() {

    private val _nowPlayingData: MutableLiveData<Resource<MovieResp>> = MutableLiveData()

    val nowPlayingData: LiveData<Resource<MovieResp>>
        get() = _nowPlayingData

    fun getNowPlayingData() = viewModelScope.launch {
        _nowPlayingData.value = Resource.Loading
        _nowPlayingData.value = repository.nowPlayingMovieList()
    }

    private val _topRatedData: MutableLiveData<Resource<MovieResp>> = MutableLiveData()

    val topRatedData: LiveData<Resource<MovieResp>>
        get() = _topRatedData

    fun getTopRatedData() = viewModelScope.launch {
        _topRatedData.value = Resource.Loading
        _topRatedData.value = repository.topRatedMovieList()
    }

    suspend fun saveNowPlayingMovieList(data: List<MovieRoom>) =
        repository.saveNowPlayingMovieList(data)

    suspend fun getNowPlayingMovieList() =
        withContext(Dispatchers.IO) { repository.getNowPlayingMovieList() }

    suspend fun saveTopRatedMovieList(data: List<MovieRoom>) =
        repository.saveTopRatedMovieList(data)

    suspend fun getTopRatedMovieList() =
        withContext(Dispatchers.IO) { repository.getTopRatedMovieList() }

}
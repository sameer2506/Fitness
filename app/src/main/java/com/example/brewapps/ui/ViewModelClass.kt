package com.example.brewapps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewapps.data.entities.MoviesList
import com.example.brewapps.data.network.Resource
import com.example.brewapps.data.repositories.Repository
import kotlinx.coroutines.launch


class ViewModelClass(private val repository: Repository) : ViewModel() {

    private val _nowPlayingData: MutableLiveData<Resource<MoviesList>> = MutableLiveData()

    val nowPlayingData: LiveData<Resource<MoviesList>>
        get() = _nowPlayingData

    fun getNowPlayingData() = viewModelScope.launch {
        _nowPlayingData.value = Resource.Loading
        _nowPlayingData.value = repository.nowPlayingMovieList()
    }

    private val _topRatedData: MutableLiveData<Resource<MoviesList>> = MutableLiveData()

    val topRatedData: LiveData<Resource<MoviesList>>
        get() = _topRatedData

    fun getTopRatedData() = viewModelScope.launch {
        _topRatedData.value = Resource.Loading
        _topRatedData.value = repository.topRatedMovieList()
    }

}
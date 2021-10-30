package com.example.brewapps.ui.nowPlaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewapps.data.entities.nowPlaying.NowPlayingData
import com.example.brewapps.data.network.Resource
import com.example.brewapps.data.repositories.Respository
import kotlinx.coroutines.launch


class ViewModelClass(private val repository: Respository) : ViewModel() {

    private val _nowPlayingData : MutableLiveData<Resource<NowPlayingData>> = MutableLiveData()

    val nowPlayingData : LiveData<Resource<NowPlayingData>>
        get() = _nowPlayingData

    fun getNowPlayingData() = viewModelScope.launch{
        _nowPlayingData.value = Resource.Loading
        _nowPlayingData.value = repository.nowPlayingMovieList()
    }

}
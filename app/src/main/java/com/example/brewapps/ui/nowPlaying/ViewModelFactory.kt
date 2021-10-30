package com.example.brewapps.ui.nowPlaying

import androidx.lifecycle.*
import com.example.brewapps.data.entities.nowPlaying.NowPlayingData
import com.example.brewapps.data.network.Resource
import com.example.brewapps.data.repositories.Respository
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Respository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelClass(repository) as T
    }
}
package com.example.brewapps.ui

import androidx.lifecycle.*
import com.example.brewapps.data.repositories.Respository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Respository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelClass(repository) as T
    }
}
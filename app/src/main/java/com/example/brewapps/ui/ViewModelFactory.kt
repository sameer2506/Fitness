package com.example.brewapps.ui

import androidx.lifecycle.*
import com.example.brewapps.data.repositories.Repository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelClass(repository) as T
    }
}
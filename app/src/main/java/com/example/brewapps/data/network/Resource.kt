package com.example.brewapps.data.network


sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorLog: String?,
        val errorToast: String?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}
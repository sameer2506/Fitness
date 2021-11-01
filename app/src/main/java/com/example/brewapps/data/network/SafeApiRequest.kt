package com.example.brewapps.data.network

import com.example.brewapps.util.NoInternetException
import com.example.brewapps.util.SocketTimeoutExceptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import java.net.SocketTimeoutException

abstract class SafeApiRequest {
    suspend fun <T> safeApiRequest(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                var error = ""
                error = if (throwable is NoInternetException) {
                    "Make sure you are connected to internet."
                } else if (throwable is SocketTimeoutExceptions || throwable is SocketTimeoutException) {
                    "Connection timeout."
                } else if (throwable is ConnectionShutdownException) {
                    "Server is busy."
                } else {
                    "Something wrong. Try again later..."
                }
                when (throwable) {
                    is HttpException ->
                        Resource.Failure(false, throwable.code(), null, null)
                    else -> {
                        Resource.Failure(false, null, throwable.localizedMessage, error)
                    }
                }
            }
        }
    }
}
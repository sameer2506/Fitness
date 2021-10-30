package com.example.brewapps.util

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class SocketTimeoutException(message: String) : IOException(message)

class SocketTimeoutExceptions(message: String) : IOException(message)
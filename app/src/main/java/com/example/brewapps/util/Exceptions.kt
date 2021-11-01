package com.example.brewapps.util

import java.io.IOException

class NoInternetException(message: String) : IOException(message)
class SocketTimeoutExceptions(message: String) : IOException(message)
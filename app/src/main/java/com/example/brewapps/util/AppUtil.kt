package com.example.brewapps.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.brewapps.data.network.Resource
import com.google.android.material.snackbar.Snackbar
import java.math.RoundingMode
import java.text.DecimalFormat

fun log(message: String) {
    Log.d("cms_app_test", message)
}

fun logError(message: String) {
    Log.e("cms_app_test", message)
}


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun handleApiError(
    failure: Resource.Failure,
    context: Context,
    view: View
) {
    when {
        failure.isNetworkError -> {
            logError("failure.isNetworkError : Make sure you are connected to internet.")
            view.snackbar(
                "Make sure you are connected to internet."
            )
        }
        failure.errorCode == 400 -> {
            logError("Bad request : 400")
            context.toast("Bad request : 400")
        }
        failure.errorCode == 401 -> {
            logError("Unauthorized : 401")
            context.toast("Unauthorized : 401")
        }
        failure.errorCode == 403 -> {
            logError("Forbidden : 403")
            context.toast("Forbidden : 403")
        }
        failure.errorCode == 404 -> {
            logError("Not found : 404")
            context.toast("Not found : 404")
        }
        failure.errorCode == 405 -> {
            logError("Method not followed : 405")
            context.toast("Method not followed : 405")
        }
        failure.errorCode == 406 -> {
            logError("Not acceptable : 406")
            context.toast("Not acceptable : 406")
        }
        failure.errorCode == 407 -> {
            logError("Proxy authentication required : 407")
            context.toast("Proxy authentication required : 407")
        }
        failure.errorCode == 408 -> {
            logError("Request timeout : 408")
            context.toast("Request timeout : 408")
        }
        else -> {
            logError("error : ${failure.errorLog}")
            if (failure.errorToast == "Make sure you are connected to internet.") {
                view.snackbar(failure.errorToast)
            } else {
                context.toast(failure.errorToast!!)
            }
        }
    }
}

fun roundTheNumber(num: Double): String {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING
    return df.format(num)
}

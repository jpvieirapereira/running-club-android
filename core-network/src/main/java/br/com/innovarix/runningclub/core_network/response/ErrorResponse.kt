package br.com.innovarix.runningclub.core_network.response

import com.squareup.moshi.Json
import java.io.IOException

data class ErrorResponse(
    @Json(name = "statusCode")
    val statusCode: Int,

    @Json(name = "errorCode")
    val errorCode: String,

    @Json(name = "errorMessage")
    val errorMessage: String
) : IOException()

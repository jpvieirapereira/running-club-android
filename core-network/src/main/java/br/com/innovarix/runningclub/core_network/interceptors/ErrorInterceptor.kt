package br.com.innovarix.runningclub.core_network.interceptors

import br.com.innovarix.runningclub.core_network.response.ErrorResponse
import retrofit2.HttpException
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.IOException
import java.net.UnknownHostException
import java.nio.charset.StandardCharsets

interface ErrorInterceptor : Interceptor

class ErrorInterceptorImpl(private val moshi: Moshi) : ErrorInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response: Response
        try {
            response = chain.proceed(request)

            if (response.isSuccessful.not()) {
                throw tryGetError(response.code, response.body)
            }

        } catch (exception: Exception) {
            throw when (exception) {
                is HttpException -> tryGetError(exception.code(), exception.response()?.errorBody())
                is ErrorResponse -> exception
                is UnknownHostException -> noConnectionError()
                is IOException -> noConnectionError()
                else -> defaultError()
            }
        }

        return response
    }

    private fun tryGetError(responseCode: Int, errorBody: ResponseBody?): ErrorResponse {
        val error = runCatching {
            val adapter = moshi.adapter(ErrorResponse::class.java)
            adapter.fromJson(errorBody.readResponseBody())
        }.getOrNull()

        return error ?: defaultError(responseCode)
    }

    private fun ResponseBody?.readResponseBody(): String {
        val source = this?.source()
        source?.request(Long.MAX_VALUE)

        val buffer = source?.buffer
        val charset = this?.contentType()
            ?.charset(StandardCharsets.UTF_8)
            ?: StandardCharsets.UTF_8
        return buffer?.clone()?.readString(charset).orEmpty()
    }

    private fun defaultError(statusCode: Int = 500) = ErrorResponse(
        statusCode = statusCode,
        errorCode = statusCode.toString(),
        errorMessage = "Erro inesperado",
    )

    private fun noConnectionError() = ErrorResponse(
        statusCode = 501,
        errorCode = "501",
        errorMessage = "Sem conexão!",
    )
}

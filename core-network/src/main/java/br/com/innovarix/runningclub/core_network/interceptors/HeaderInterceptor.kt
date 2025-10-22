package br.com.innovarix.runningclub.core_network.interceptors

import br.com.innovarix.runningclub.core_network.interceptors.cache.InterceptorCache
import okhttp3.Interceptor
import okhttp3.Response

interface HeaderInterceptor: Interceptor

class HeaderInterceptorImpl(
    private val platformVersion: String,
    private val interceptorCache: InterceptorCache
): HeaderInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder().apply {
            addHeader(CONTENT_TYPE, CONTENT_VALUE)
            addHeader(PLATFORM_KEY, PLATFORM_VALUE)
            addHeader(PLATFORM_VERSION, platformVersion)
            addHeader(API_KEY, API_VALUE)

            val token = interceptorCache.getToken()
            if(token.isNullOrEmpty().not()) {
                addHeader(
                    AUTHORIZATION_KEY,
                    token.orEmpty()
                )
            }
        }

        val response = chain.proceed(builder.build())
        return response
    }

    private companion object {
        const val PLATFORM_KEY = "platform"
        const val PLATFORM_VALUE = "android"
        const val PLATFORM_VERSION = "version"
        const val CONTENT_TYPE = "content-type"
        const val CONTENT_VALUE = "application/json"
        const val API_KEY = "apiKey"
        const val API_VALUE = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"
        const val AUTHORIZATION_KEY = "authorization"
        const val ACCESS_TOKEN_KEY = "access_token"
    }
}
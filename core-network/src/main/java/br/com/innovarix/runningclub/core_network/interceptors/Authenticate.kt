package br.com.innovarix.runningclub.core_network.interceptors

import br.com.innovarix.runningclub.core_network.interceptors.repository.LoginRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

interface Authenticate: Authenticator

class AuthenticateImpl(
    private val loginRepository: LoginRepository
) : Authenticate {

    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = getNewToken()

       return if(token != null) {
            response.request.newBuilder()
                .addHeader(AUTHORIZATION_KEY, token)
                .build()
        } else {
            null
        }
    }

    private fun getNewToken(): String? {
      return  runBlocking {
            runCatching {
                loginRepository.refreshToken()
            }.getOrNull()
        }
    }

    private companion object {
        const val AUTHORIZATION_KEY = "authorization"
    }
}
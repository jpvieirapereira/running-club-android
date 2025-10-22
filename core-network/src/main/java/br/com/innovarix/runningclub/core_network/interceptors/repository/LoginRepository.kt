package br.com.innovarix.runningclub.core_network.interceptors.repository

interface LoginRepository {

   suspend fun login(userName: String, password: String)

   suspend fun refreshToken(): String?
}
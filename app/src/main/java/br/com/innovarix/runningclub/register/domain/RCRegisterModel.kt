package br.com.innovarix.runningclub.register.domain

data class RCRegisterModel(
    val fullName: String,
    val documentNumber: String,
    val birthDate: String,
    val email: String,
    val phone: String,
    val password: String
)
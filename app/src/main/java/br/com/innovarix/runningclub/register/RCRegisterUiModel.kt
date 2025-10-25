package br.com.innovarix.runningclub.register

data class RCRegisterUiModel(
    val type: RCRegisterInputType,
    val value: String = "",
    val isValidationError: Boolean = false
) {
    fun validationError() = type.validationError.takeIf { isValidationError }
}
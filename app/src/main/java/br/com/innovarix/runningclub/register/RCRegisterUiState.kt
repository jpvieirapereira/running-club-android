package br.com.innovarix.runningclub.register

import br.com.innovarix.runningclub.core.base.UiState

data class RCRegisterUiState(
    val showLoading: Boolean = false,
    val enableButton: Boolean = false,
    val fields: List<RCRegisterUiModel> = RCRegisterInputType.entries.map {
        RCRegisterUiModel(type = it)
    }
) : UiState
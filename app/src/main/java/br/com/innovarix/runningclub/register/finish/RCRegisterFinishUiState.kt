package br.com.innovarix.runningclub.register.finish

import br.com.innovarix.runningclub.core.base.UiState

data class RCRegisterFinishUiState(
    val showLoading: Boolean = false,
    val options: List<RCRegisterFinishUiModel> = emptyList(),
    val enableButton: Boolean = false
): UiState
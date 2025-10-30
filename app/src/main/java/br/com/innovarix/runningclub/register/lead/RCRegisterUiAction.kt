package br.com.innovarix.runningclub.register.lead

import br.com.innovarix.runningclub.core.base.UiAction

sealed interface RCRegisterUiAction : UiAction {
    data object OnTollbarClicked : RCRegisterUiAction
    data class OnChangeFields(val data: RCRegisterUiModel) : RCRegisterUiAction
    data object OnAdvanceClicked : RCRegisterUiAction
}
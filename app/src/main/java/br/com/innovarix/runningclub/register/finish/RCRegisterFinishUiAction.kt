package br.com.innovarix.runningclub.register.finish

import br.com.innovarix.runningclub.core.base.UiAction

sealed interface RCRegisterFinishUiAction : UiAction {
    data object OnInit : RCRegisterFinishUiAction
    data object OnTollbarClicked : RCRegisterFinishUiAction
    data class OnChangeFields(val data: RCRegisterFinishUiModel) : RCRegisterFinishUiAction
    data object OnAdvanceClicked : RCRegisterFinishUiAction
}
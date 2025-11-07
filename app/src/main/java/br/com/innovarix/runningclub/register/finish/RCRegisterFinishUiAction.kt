package br.com.innovarix.runningclub.register.finish

import br.com.innovarix.runningclub.core.base.UiAction
import br.com.innovarix.runningclub.register.domain.RCRegisterModel

sealed interface RCRegisterFinishUiAction : UiAction {
    data class OnInit(val register: RCRegisterModel) : RCRegisterFinishUiAction
    data object OnTollbarClicked : RCRegisterFinishUiAction
    data class OnChangeFields(val data: RCRegisterFinishUiModel) : RCRegisterFinishUiAction
    data object OnAdvanceClicked : RCRegisterFinishUiAction
}
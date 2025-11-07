package br.com.innovarix.runningclub.register.lead

import br.com.innovarix.runningclub.core.base.UiEvent
import br.com.innovarix.runningclub.register.domain.RCRegisterModel

sealed interface RCRegisterUiEvent : UiEvent {
    data class NavigateToFinish(val register: RCRegisterModel) : RCRegisterUiEvent
}
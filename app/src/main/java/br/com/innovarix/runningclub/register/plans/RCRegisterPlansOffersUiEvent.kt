package br.com.innovarix.runningclub.register.plans

import br.com.innovarix.runningclub.core.base.UiEvent

interface RCRegisterPlansOffersUiEvent: UiEvent {
    data object OnBackPressed: RCRegisterPlansOffersUiEvent
}
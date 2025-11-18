package br.com.innovarix.runningclub.register.plans

import br.com.innovarix.runningclub.core.base.UiAction
import br.com.innovarix.runningclub.core_theme.components.price.RCPlanPriceUiModel

sealed interface RCRegisterPlansOffersUiAction : UiAction {
    data object OnTollbarClicked : RCRegisterPlansOffersUiAction
    data object OnInit : RCRegisterPlansOffersUiAction
    data class OnPlanSelected(val plan: RCPlanPriceUiModel) : RCRegisterPlansOffersUiAction
    data object OnAdvanceClicked : RCRegisterPlansOffersUiAction
}
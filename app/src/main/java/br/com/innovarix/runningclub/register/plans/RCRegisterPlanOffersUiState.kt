package br.com.innovarix.runningclub.register.plans

import br.com.innovarix.runningclub.core.base.UiState
import br.com.innovarix.runningclub.core_theme.components.price.RCPlanPriceUiModel

data class RCRegisterPlanOffersUiState(
    val plansPrices: List<RCPlanPriceUiModel> = emptyList(),
    val enableButton: Boolean = false,
): UiState
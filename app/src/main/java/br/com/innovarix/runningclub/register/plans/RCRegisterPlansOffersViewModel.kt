package br.com.innovarix.runningclub.register.plans

import br.com.innovarix.runningclub.core.base.ViewModel
import br.com.innovarix.runningclub.core_theme.components.price.RCPlanPriceUiModel

class RCRegisterPlansOffersViewModel: ViewModel<
        RCRegisterPlanOffersUiState,
        RCRegisterPlansOffersUiEvent,
        RCRegisterPlansOffersUiAction>(RCRegisterPlanOffersUiState()) {

    override fun dispatchAction(action: RCRegisterPlansOffersUiAction) {
        when(action) {
            RCRegisterPlansOffersUiAction.OnInit -> onInit()
            RCRegisterPlansOffersUiAction.OnTollbarClicked -> onToolbarClicked()
            is RCRegisterPlansOffersUiAction.OnPlanSelected -> onPlanSelected(action.plan)
            RCRegisterPlansOffersUiAction.OnAdvanceClicked -> {}
        }
    }

    private fun onInit() {
        val mockPlans = (1..5).map {
            RCPlanPriceUiModel(
                id = "$it",
                description = "Mensal",
                price = "R$ ${it}00,00",
                isSelected = false
            )
        }

        updateState {
            it.copy(plansPrices = mockPlans)
        }
    }

    private fun onPlanSelected(plan: RCPlanPriceUiModel) {
        val newState = state.value.plansPrices.map {
            if(plan.id == it.id) {
                it.copy(isSelected = plan.isSelected)
            } else {
                it.copy(isSelected = false)
            }
        }
        val enableButton = newState.any { it.isSelected }
        updateState { it.copy(plansPrices = newState, enableButton = enableButton) }
    }

    private fun onToolbarClicked() {
        sendEvent { RCRegisterPlansOffersUiEvent.OnBackPressed }
    }
}
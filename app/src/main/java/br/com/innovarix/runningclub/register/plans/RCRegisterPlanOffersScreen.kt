package br.com.innovarix.runningclub.register.plans

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.R
import br.com.innovarix.runningclub.core_theme.components.button.RCButtonText
import br.com.innovarix.runningclub.core_theme.components.price.RCPlanPrice
import br.com.innovarix.runningclub.core_theme.theme.RCSize
import br.com.innovarix.runningclub.register.lead.RCRegisterToolbar

@Composable
fun RCRegisterPlansOffersScreen(
    modifier: Modifier = Modifier,
    state: RCRegisterPlanOffersUiState,
    action: (RCRegisterPlansOffersUiAction) -> Unit
) {
    RCRegisterToolbar(
        modifier = modifier.fillMaxSize(),
        subTitle = R.string.rc_register_plans_offers_header,
        content = {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                state.plansPrices.forEach { planPrice ->
                    RCPlanPrice(priceUiModel = planPrice) { planPriceSelected ->
                        action.invoke(RCRegisterPlansOffersUiAction.OnPlanSelected(planPriceSelected))
                    }
                }
            }
        },
        button = {
            RCButtonText(
                modifier = Modifier.padding(
                    horizontal = RCSize.Spacing.md,
                    vertical = RCSize.Spacing.xl
                ),
                text = stringResource(R.string.rc_register_plans_offers_advance),
                isEnabled = state.enableButton
            ) {
                action.invoke(RCRegisterPlansOffersUiAction.OnAdvanceClicked)
            }
        },
        onToolbarClicked = {
            action.invoke(RCRegisterPlansOffersUiAction.OnTollbarClicked)
        }
    )
}
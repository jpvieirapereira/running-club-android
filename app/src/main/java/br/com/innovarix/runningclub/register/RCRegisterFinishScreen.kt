package br.com.innovarix.runningclub.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.R
import br.com.innovarix.runningclub.core_theme.components.button.RCButtonText
import br.com.innovarix.runningclub.core_theme.components.dropdown.RCDropDownTitle
import br.com.innovarix.runningclub.core_theme.components.input.RCInput
import br.com.innovarix.runningclub.core_theme.components.text.RCText
import br.com.innovarix.runningclub.core_theme.theme.RCSize
import br.com.innovarix.runningclub.register.domain.RCLevelType

@Composable
fun RCRegisterFinishScreen(
    modifier: Modifier = Modifier,
    state: RCRegisterUiState,
    action: (RCRegisterUiAction) -> Unit
) {
    RCRegisterToolbar(
        modifier = modifier.fillMaxSize(),
        subTitle = R.string.rc_register_finish_subtitle,
        description = R.string.rc_register_finish_header,
        onToolbarClicked = {
            action.invoke(RCRegisterUiAction.OnTollbarClicked)
        },
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = RCSize.Spacing.md)
            ) {

                RCInput(
                    value = "",
                    label = stringResource(R.string.rc_register_finish_surname),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { value ->

                    }
                )

                RCDropDownTitle(
                    title = {
                        RCText(text = stringResource(R.string.rc_register_finish_level))
                    },
                    isExpanded = false,
                    options = RCLevelType.entries.map { it.level }
                ) {

                }

                RCDropDownTitle(
                    title = {
                        RCText(text = stringResource(R.string.rc_register_finish_availability_training))
                    },
                    isExpanded = false,
                    options = RCLevelType.entries.map { it.level }
                ) {

                }

                RCDropDownTitle(
                    title = {
                        RCText(text = stringResource(R.string.rc_register_finish_challenge))
                    },
                    isExpanded = false,
                    options = RCLevelType.entries.map { it.level }
                ) {

                }

            }
        },
        button = {
            RCButtonText(
                modifier = Modifier.padding(
                    horizontal = RCSize.Spacing.md,
                    vertical = RCSize.Spacing.xl
                ),
                text = stringResource(R.string.rc_register_advance),
                isEnabled = state.enableButton
            ) {
                action.invoke(RCRegisterUiAction.OnAdvanceClicked)
            }
        }
    )
}


package br.com.innovarix.runningclub.register.finish

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
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.R
import br.com.innovarix.runningclub.core_theme.components.button.RCButtonText
import br.com.innovarix.runningclub.core_theme.components.dropdown.RCDropDownTitle
import br.com.innovarix.runningclub.core_theme.components.input.RCInput
import br.com.innovarix.runningclub.core_theme.components.text.RCText
import br.com.innovarix.runningclub.core_theme.theme.RCSize
import br.com.innovarix.runningclub.register.domain.RCLevelType
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishInputType.*
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishUiAction.OnChangeFields
import br.com.innovarix.runningclub.register.lead.RCRegisterToolbar
import br.com.innovarix.runningclub.register.lead.RCRegisterUiAction
import br.com.innovarix.runningclub.register.lead.RCRegisterUiState

@Composable
fun RCRegisterFinishScreen(
    modifier: Modifier = Modifier,
    state: RCRegisterFinishUiState,
    action: (RCRegisterFinishUiAction) -> Unit
) {
    RCRegisterToolbar(
        modifier = modifier.fillMaxSize(),
        subTitle = R.string.rc_register_finish_subtitle,
        description = R.string.rc_register_finish_header,
        onToolbarClicked = {
            action.invoke(RCRegisterFinishUiAction.OnTollbarClicked)
        },
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = RCSize.Spacing.md)
            ) {

                state.options.forEach { field ->
                    when (field.type) {
                        SURNAME -> {
                            RCInput(
                                value = field.surName.orEmpty(),
                                label = stringResource(field.type.label),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Words,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                onValueChange = { value ->
                                   action.invoke(OnChangeFields(field.copy(surName = value)))
                                }
                            )
                        }

                        LEVEL, AVAILABILITY, CHALLENGE -> {
                            RCDropDownTitle(
                                title = {
                                    RCText(text = stringResource(field.type.label))
                                },
                                isExpanded = false,
                                options = field.options.map { it.option },
                                optionSelected = field.optionSelected?.option
                            ) { option ->
                                val optionSelected =
                                    field.options.firstOrNull { it.option == option }
                                action.invoke(
                                    OnChangeFields(field.copy(optionSelected = optionSelected))
                                )
                            }
                        }

                        TERMS -> {}
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
                text = stringResource(R.string.rc_register_advance),
                isEnabled = state.enableButton
            ) {
                action.invoke(RCRegisterFinishUiAction.OnAdvanceClicked)
            }
        }
    )
}


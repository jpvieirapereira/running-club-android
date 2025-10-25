package br.com.innovarix.runningclub.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.R
import br.com.innovarix.runningclub.core_theme.components.button.RCButtonText
import br.com.innovarix.runningclub.core_theme.components.input.RCInput
import br.com.innovarix.runningclub.core_theme.components.input.RCMaskVisualTransformation
import br.com.innovarix.runningclub.core_theme.components.text.RCText
import br.com.innovarix.runningclub.core_theme.components.toolbar.RCToolbar
import br.com.innovarix.runningclub.core_theme.theme.RCColors
import br.com.innovarix.runningclub.core_theme.theme.RCSize

@Composable
fun RCRegisterScreen(
    modifier: Modifier = Modifier,
    state: RCRegisterUiState,
    action: (RCRegisterUiAction) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = RCColors.Light)
    ) {
        RCToolbar(onClick = { action.invoke(RCRegisterUiAction.OnTollbarClicked) })

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            Header()

            Fields(
                state.fields,
                onValueChange = { field -> action.invoke(RCRegisterUiAction.OnChangeFields(field))})

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
    }
}

@Composable
private fun Header() {
    Column(modifier = Modifier.padding(horizontal = RCSize.Spacing.md)) {
        RCText(
            text = stringResource(R.string.rc_register_title),
            fontSize = RCSize.Text.xxxl,
            fontStyle = FontWeight.W800
        )

        Spacer(modifier = Modifier.height(RCSize.Spacing.lg))

        RCText(
            text = stringResource(R.string.rc_register_subtitle),
            color = RCColors.Beige,
            fontSize = RCSize.Text.xl,
            fontStyle = FontWeight.W800
        )

        Spacer(modifier = Modifier.height(RCSize.Spacing.xxl))

        RCText(
            text = stringResource(R.string.rc_register_header),
            fontSize = RCSize.Text.md,
            fontStyle = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(RCSize.Spacing.md))
    }
}

@Composable
private fun Fields(
    fields: List<RCRegisterUiModel>,
    onValueChange: (field: RCRegisterUiModel) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = RCSize.Spacing.md)
    ) {
        fields.forEach { field ->
            RCInput(
                value = field.value,
                label = stringResource(field.type.label),
                error = field.validationError()?.let { stringResource(it) },
                keyboardOptions = field.type.keyboardOptions,
                visualTransformation = field.type.maskTransformation ?: VisualTransformation.None,
                onValueChange = { value ->
                    onValueChange.invoke(field.copy(value = value))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
   // RCRegisterScreen()
}
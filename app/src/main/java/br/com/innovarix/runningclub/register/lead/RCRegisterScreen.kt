package br.com.innovarix.runningclub.register.lead

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.R
import br.com.innovarix.runningclub.core_theme.components.button.RCButtonText
import br.com.innovarix.runningclub.core_theme.components.input.RCInput
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

    RCRegisterToolbar(
        modifier = modifier,
        subTitle = R.string.rc_register_subtitle,
        description = R.string.rc_register_header,
        onToolbarClicked = {
            action.invoke(RCRegisterUiAction.OnTollbarClicked)
        },
        content = {
            Fields(
                state.fields,
                onValueChange = { field -> action.invoke(RCRegisterUiAction.OnChangeFields(field)) })
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

@Composable
fun RCRegisterToolbar(
    modifier: Modifier = Modifier,
    @StringRes subTitle: Int,
    @StringRes description: Int? = null,
    content: @Composable ColumnScope.() -> Unit,
    button: @Composable () -> Unit,
    onToolbarClicked: (() -> Unit?)? = null
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = RCColors.Light)
    ) {
        RCToolbar(onClick = onToolbarClicked)

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
        ) {
            RCRegisterHeader(
                subTitle = subTitle,
                description = description
            )

            content()
            Spacer(modifier = Modifier.weight(1f))
            button()
        }
    }
}

@Composable
fun RCRegisterHeader(
    @StringRes subTitle: Int,
    @StringRes description: Int? = null
) {
    Column(modifier = Modifier.padding(horizontal = RCSize.Spacing.md)) {
        RCText(
            text = stringResource(R.string.rc_register_title),
            fontSize = RCSize.Text.xxxl,
            fontStyle = FontWeight.W800
        )

        Spacer(modifier = Modifier.height(RCSize.Spacing.lg))

        RCText(
            text = stringResource(subTitle),
            color = RCColors.Beige,
            fontSize = RCSize.Text.xl,
            fontStyle = FontWeight.W800
        )

        Spacer(modifier = Modifier.height(RCSize.Spacing.xxl))

        description?.let {
            RCText(
                text = stringResource(description),
                fontSize = RCSize.Text.md,
                fontStyle = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(RCSize.Spacing.md))
        }
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
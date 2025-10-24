package br.com.innovarix.runningclub.core_theme.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.innovarix.runningclub.core_theme.components.text.RCText
import br.com.innovarix.runningclub.core_theme.theme.RCSize
import br.com.innovarix.runningclub.core_theme.theme.RunningClubTheme

@Composable
fun RCInput(
    modifier: Modifier = Modifier,
    value: String = "",
    enabled: Boolean = true,
    label: String? = null,
    error: String? = null,
    onValueChange: (String) -> Unit = {},
) = Column(modifier = Modifier.background(color = Color.Transparent)) {

    TextField(
        modifier = modifier.fillMaxWidth(),
        label = if (!label.isNullOrEmpty()) {
            { RCText(text = label) }
        } else null,
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            errorContainerColor = Color.Transparent
        ),
        shape = RectangleShape,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        isError = error != null
    )

    error?.let {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = RCSize.Spacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RCText(
                text = it,
                fontSize = 12.sp,
                textStyle = TextStyle.Default.copy(
                    fontWeight = FontWeight.W600
                ),
                color = Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RcInputPreview() {
    RunningClubTheme {
        RCInput(label = "Person Name")
    }
}
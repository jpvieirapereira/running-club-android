package br.com.innovarix.runningclub.core_theme.components.check

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import br.com.innovarix.runningclub.core_theme.components.text.RCText

@Composable
fun RCCheckBox(
    modifier: Modifier = Modifier,
    description: String,
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit = {},
    onDescriptionClicked: () -> Unit = {}
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = isChecked,
            onCheckedChange = { onChecked.invoke(it) }
        )

        RCText(
            modifier.clickable {
                onDescriptionClicked.invoke()
            },
            text = description,
            textDecoration = TextDecoration.Underline
        )
    }
}
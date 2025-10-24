package br.com.innovarix.runningclub.core_theme.components.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.innovarix.runningclub.core_theme.R
import br.com.innovarix.runningclub.core_theme.components.text.RCText
import br.com.innovarix.runningclub.core_theme.theme.RCSize
import br.com.innovarix.runningclub.core_theme.theme.RunningClubTheme

@Composable
fun RCToolbar(
    modifier: Modifier = Modifier,
    text: String? = null,
    showNavigateIcon: Boolean = true,
    colors: RCToolbarStyleType = RCToolbarStyleType.PRIMARY,
    onClick: (() -> Unit?)? = null
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .background(color = colors.backgroundColor)
    ) {

        if (showNavigateIcon) {
            IconButton(
                modifier = Modifier.padding(
                    top = RCSize.Spacing.mdd,
                    start = RCSize.Spacing.mdd,
                    bottom =  RCSize.Spacing.mdd
                ).size(RCSize.Icons.xs),
                onClick = { onClick?.invoke() }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                    contentDescription = stringResource(id = R.string.rc_back_cd),
                    tint = colors.iconColor
                )
            }
        }

        text?.let {
            RCText(
                modifier = Modifier.fillMaxWidth().padding(bottom = RCSize.Spacing.xl),
                text = text,
                textAlign = TextAlign.Center,
                fontSize = RCSize.Text.xl,
                fontStyle = FontWeight.Bold,
                color = colors.textColor
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    RunningClubTheme {
        Column {
            RCToolbar(text = "Teste Nivelamento")

            RCToolbar(colors = RCToolbarStyleType.SECONDARY, text = "Teste Nivelamento")
        }
    }
}
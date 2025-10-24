package br.com.innovarix.runningclub.core_theme.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import br.com.innovarix.runningclub.core_theme.theme.RCSize

@Composable
fun RCText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    fontSize: TextUnit = RCSize.Text.sm,
    fontStyle: FontWeight = FontWeight.Normal,
    textAlign: TextAlign? = null,
    textStyle: TextStyle = TextStyle.Default
) {
    Text(
        textAlign = textAlign,
        text = text,
        color = color,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontStyle,
        style = textStyle
    )
}
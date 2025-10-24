package br.com.innovarix.runningclub.core_theme.components.button

import androidx.compose.ui.graphics.Color
import br.com.innovarix.runningclub.core_theme.theme.RCColors

enum class RCButtonStyleType(
    val color: Color,
    val contentColor: Color,
    val borderColor: Color
) {
    PRIMARY(
        RCColors.Black,
        RCColors.White,
        Color.Transparent
    ),
    SECONDARY(
        RCColors.Beige,
        RCColors.Black,
        Color.Transparent
    )
}
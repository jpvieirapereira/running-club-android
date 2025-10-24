package br.com.innovarix.runningclub.core_theme.components.toolbar

import androidx.compose.ui.graphics.Color
import br.com.innovarix.runningclub.core_theme.theme.RCColors


enum class RCToolbarStyleType(
    val backgroundColor: Color,
    val iconColor: Color,
    val textColor: Color,
) {
    PRIMARY(
        backgroundColor = RCColors.Light,
        textColor = RCColors.Black,
        iconColor = RCColors.Beige
    ),
    SECONDARY(
        backgroundColor = RCColors.Black,
        textColor = RCColors.White,
        iconColor = RCColors.Beige
    )
}
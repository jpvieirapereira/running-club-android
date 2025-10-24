package br.com.innovarix.runningclub.core_theme.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.innovarix.runningclub.core_theme.theme.RCColors
import br.com.innovarix.runningclub.core_theme.theme.RCSize

@Composable
private fun RCButton(
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke? = null,
    colors: RCButtonStyleType = RCButtonStyleType.PRIMARY,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) = Button(
    onClick = onClick,
    shape = RoundedCornerShape(RCSize.Radius.circle),
    border = borderStroke,
    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
    enabled = isEnabled,
    contentPadding = ButtonDefaults.ContentPadding,
    modifier = modifier
        .fillMaxWidth()
        .height(RCSize.Spacing.xll),
    colors = ButtonColors(
        containerColor = colors.color,
        contentColor = colors.contentColor,
        disabledContainerColor = colors.color.copy(alpha = RCColors.Alpha.disabled),
        disabledContentColor = colors.contentColor.copy(alpha = RCColors.Alpha.disabled)
    )
) {
    content()
}

@Composable
fun RCButtonText(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    colors: RCButtonStyleType = RCButtonStyleType.PRIMARY,
    fontSize: TextUnit = 16.sp,
    isBorder: Boolean = true,
    onClick: () -> Unit,
) {
    RCButton(
        isEnabled = isEnabled,
        modifier = modifier,
        colors = colors,
        borderStroke = if (isBorder) BorderStroke(
            width = RCSize.border,
            color = colors.borderColor
        ) else null,
        onClick = onClick,

        ) {
        BasicText(
            text = text,
            style = TextStyle.Default.copy(
                color = colors.contentColor,
                fontWeight = FontWeight.Medium
            ),
            overflow = TextOverflow.Clip,
            modifier = Modifier,
            autoSize = TextAutoSize.StepBased(
                minFontSize = 14.sp,
                maxFontSize = fontSize,
                stepSize = 1.sp
            )
        )
    }
}

@Preview
@Composable
private fun Preview() = Column {
    RCButtonStyleType.entries.forEach {
        RCButtonText(
            text = "Compose, Claro!",
            colors = it,
            onClick = {}
        )
    }
}



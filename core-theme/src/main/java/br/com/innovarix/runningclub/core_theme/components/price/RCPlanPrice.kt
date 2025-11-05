package br.com.innovarix.runningclub.core_theme.components.price

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.core_theme.components.text.RCText
import br.com.innovarix.runningclub.core_theme.theme.RCColors
import br.com.innovarix.runningclub.core_theme.theme.RCSize

@Composable
fun RCPlanPrice(
    modifier: Modifier = Modifier,
    priceUiModel: RCPlanPriceUiModel,
    onPriceSelected: (price: RCPlanPriceUiModel) -> Unit = {}
) {

    val isSelected by remember { mutableStateOf(priceUiModel.isSelected) }

    val borderModifier = if(isSelected) {
        Modifier.border(
            border = BorderStroke(
                width = 5.dp,
                brush = Brush.verticalGradient(
                    listOf(RCColors.Beige, RCColors.Beige.copy(alpha = 0.8f))
                )
            ),
            shape = RoundedCornerShape(28.dp)
        )
    } else {
        Modifier
    }

    Column(
        modifier = modifier
            .width(180.dp)
            .background(color = RCColors.Black, shape = RoundedCornerShape(28.dp))
            .clickable { onPriceSelected.invoke(priceUiModel) }
            .then(borderModifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RCText(
            modifier = Modifier.padding(top = 16.dp, bottom = 10.dp),
            text = priceUiModel.description,
            color = RCColors.Beige,
            fontSize = RCSize.Text.xxl,
            fontStyle = FontWeight.W800
        )

        RCText(
            modifier = Modifier.padding(bottom = 20.dp),
            text = priceUiModel.price,
            color = RCColors.White,
            fontSize = RCSize.Text.xxl,
            fontStyle = FontWeight.W800
        )
    }
}

data class RCPlanPriceUiModel(
    val description: String,
    val price: String,
    val isSelected: Boolean = false
)
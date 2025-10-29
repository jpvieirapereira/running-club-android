package br.com.innovarix.runningclub.core_theme.components.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.core_theme.R
import br.com.innovarix.runningclub.core_theme.components.text.RCText
import br.com.innovarix.runningclub.core_theme.theme.RCColors
import br.com.innovarix.runningclub.core_theme.theme.RCSize


@Composable
fun RCDropDownTitle(
    title: @Composable ColumnScope.() -> Unit,
    isExpanded: Boolean = false,
    optionSelected: String? = null,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(RCSize.Spacing.sm),
        modifier = Modifier.fillMaxWidth()
    ) {

        title()

        RCDropDown(
            isExpanded = isExpanded,
            optionSelected = optionSelected,
            options = options,
            onOptionSelected = onOptionSelected
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RCDropDown(
    isExpanded: Boolean = false,
    optionSelected: String? = null,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
) {

    val safeIndex = optionSelected?.let {
        options.indexOf(optionSelected)
    } ?: 0

    var expanded by remember { mutableStateOf(isExpanded) }
    var selectedOptionText by remember { mutableStateOf(options[safeIndex]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = RCColors.LightingGray, RoundedCornerShape(RCSize.Radius.md))
                .padding(horizontal = RCSize.Spacing.md)
                .height(40.dp)
                .menuAnchor(),
        ) {

            RCText(
                text = selectedOptionText,
                fontSize = RCSize.Text.sm,
                color = RCColors.DarkGray
            )

            Icon(
                painter = painterResource(R.drawable.ic_arrown_down),
                null,
                modifier = Modifier.rotate(if (expanded) 180f else 0f),
                tint = RCColors.Gray
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = RCColors.LightingGray,
            shape = RoundedCornerShape(
                bottomEnd = RCSize.Radius.md,
                bottomStart = RCSize.Radius.md
            )
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier.height(35.dp),
                    text = {
                        RCText(
                            text = selectionOption,
                            color = RCColors.DarkGray,
                            fontSize = RCSize.Text.sm
                        )
                    },
                    onClick = {
                        selectedOptionText = selectionOption
                        onOptionSelected.invoke(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}
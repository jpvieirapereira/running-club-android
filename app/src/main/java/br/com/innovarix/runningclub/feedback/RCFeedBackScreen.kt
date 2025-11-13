package br.com.innovarix.runningclub.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.innovarix.runningclub.core_theme.components.button.RCButtonStyleType
import br.com.innovarix.runningclub.core_theme.components.button.RCButtonText
import br.com.innovarix.runningclub.core_theme.components.image.RCLocalImage
import br.com.innovarix.runningclub.core_theme.components.text.RCText
import br.com.innovarix.runningclub.core_theme.theme.RCColors
import br.com.innovarix.runningclub.core_theme.theme.RCSize
import br.com.innovarix.runningclub.core_theme.theme.RunningClubTheme

@Composable
fun RCFeedBackScreen(
    modifier: Modifier = Modifier,
    feedBackUiModel: RCFeedBackUiModel,
    onButtonClicked: (type: RCFeedBackType) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.weight(1f))

        RCLocalImage(
            localImage = feedBackUiModel.feedBackType.icon
        )

        Spacer(modifier = Modifier.size(RCSize.Spacing.mdd))

        RCText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(feedBackUiModel.feedBackType.feedBack),
            color = RCColors.Beige,
            fontSize = RCSize.Text.xxxxl,
            fontStyle = FontWeight.W800,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        RCText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(feedBackUiModel.feedBackType.feedBackDescription),
            color = RCColors.Black,
            fontSize = RCSize.Text.xl,
            fontStyle = FontWeight.W800,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        RCButtonText(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = RCSize.Spacing.md, vertical = RCSize.Spacing.xl),
            text = stringResource(feedBackUiModel.feedBackType.buttonText),
            colors = RCButtonStyleType.SECONDARY,
            onClick = {
                onButtonClicked.invoke(feedBackUiModel.feedBackType)
            }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun RCFeedBackPreview() {
    RunningClubTheme {
        Column {
            RCFeedBackScreen(feedBackUiModel = RCFeedBackUiModel(RCFeedBackType.SUCCESS))
        }
    }
}
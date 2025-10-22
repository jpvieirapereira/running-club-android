package br.com.innovarix.runningclub.core_theme.components.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.core_theme.R

@Composable
fun CFLocalImage(
    modifier: Modifier = Modifier,
    colorFilter: ColorFilter? = null,
    @DrawableRes localImage: Int,
    width: Int? = null,
    height: Int? = null,
    contentDescription: String? = null,
    onClick: (() -> Unit?)? = null
) {
    var imageModifier = modifier
    width?.let {
        imageModifier = imageModifier.width(it.dp)
    }

    height?.let {
        imageModifier = imageModifier.height(it.dp)
    }

    Image(
        modifier = imageModifier,
        painter = painterResource(localImage),
        colorFilter = colorFilter,
        contentDescription = contentDescription
    )
}


@Preview
@Composable
private fun Preview() = Column(modifier = Modifier.fillMaxSize()) {
    CFLocalImage(
        localImage = R.drawable.ic_check_erro,
        width = 32,
        height = 32
    )

    CFLocalImage(
        localImage = R.drawable.ic_check_success,
        width = 100,
        height = 100)
}

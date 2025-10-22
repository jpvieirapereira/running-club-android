package br.com.innovarix.runningclub.core_theme.components.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.innovarix.runningclub.core.extensions.orZero
import br.com.innovarix.runningclub.core_theme.R
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.CachePolicy
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import kotlinx.coroutines.Dispatchers

@Composable
fun RCRemoteImage(
    modifier: Modifier = Modifier,
    url: String,
    @DrawableRes placeHolder: Int? = null,
    width: Int? = null,
    height: Int? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.FillWidth,
    onClick: (() -> Unit?)? = null,
    tint: Color? = Color.Unspecified,
) {

    var imageModifier = modifier
    if (width.orZero() > 0) {
        imageModifier = imageModifier.width(width.orZero().dp)
    }

    if (height.orZero() > 0) {
        imageModifier = imageModifier.height(height.orZero().dp)
    }

    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .allowHardware(false)
        .memoryCacheKey(url)
        .diskCacheKey(url)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)

    placeHolder?.let {
        imageRequest
            .placeholder(it)
            .error(it)
            .fallback(it)
    }

    AsyncImage(
        modifier = imageModifier,
        model = imageRequest.build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        colorFilter = if (tint != Color.Unspecified) tint?.let { ColorFilter.tint(tint) } else null,
    )
}

@Preview
@Composable
private fun Preview() = Column(modifier = Modifier.fillMaxSize()) {
    RCRemoteImage(
        modifier = Modifier.weight(1f),
        url = "https://s4.static.brasilescola.uol.com.br/be/2021/04/casal-de-leoes.jpg",
        placeHolder = R.drawable.ic_check_success,
        width = 200,
        height = 200,
        contentScale = ContentScale.FillHeight
    )

    RCRemoteImage(
        modifier = Modifier.weight(1f),
        url = "https://s4.static.brasilescola.uol.com.br/be/2021/04/casal-de-leoes.jpg",
        placeHolder = R.drawable.ic_check_success,
        width = 300,
        height = 300,
        contentScale = ContentScale.Crop
    )

    RCRemoteImage(
        modifier = Modifier.weight(1f),
        url = "https://s4.static.brasilescola.uol.com.br/be/2021/04/casal-de-leoes.jpg",
        placeHolder = R.drawable.ic_check_success,
        width = 50,
        height = 50,
        contentScale = ContentScale.FillWidth
    )
}

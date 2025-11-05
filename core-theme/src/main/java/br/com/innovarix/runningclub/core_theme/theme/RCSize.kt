package br.com.innovarix.runningclub.core_theme.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object RCSize {

    val border = 1.dp

    object Weight {
        const val FILL = 1f
        const val HALF = 0.5f
        const val NONE = 0f
    }

    object Spacing {
        val empty = 0.dp

        val xs = 4.dp
        val sm = 8.dp
        val md = 16.dp
        val mdd = 20.dp
        val lg = 24.dp
        val xl = 32.dp
        val xll = 44.dp
        val xxl = 48.dp
    }

    object Icons {
        val xs = 14.dp
        val sm = 16.dp
        val md = 18.dp
        val lg = 24.dp
        val xl = 40.dp
        val xxl = 48.dp
    }

    object Radius {
        val xs = 4.dp
        val sm = 6.dp
        val md = 8.dp
        val lg = 16.dp
        val xl = 24.dp

        val circle = 48.dp
    }

    object Text {
        /** XS: 12sp*/
        val xs = 12.sp
        /** SM: 14sp*/
        val sm = 14.sp
        /** MD: 16sp*/
        val md = 16.sp
        /** LG: 18sp*/
        val lg = 18.sp
        /** XL: 20sp*/
        val xl = 22.sp
        /** XXL: 24sp*/
        val xxl = 24.sp
        /** XXXL: 28sp*/
        val xxxl = 28.sp
        val xxxxl = 36.sp
    }

    object Border {
        val xs = 0.5.dp
        val sm = 1.dp
        val md = 2.dp
        val lg = 3.dp
        val xl = 4.dp
        val xxl = 5.sp
    }
}

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }


@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

package bulkapedia.core.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.bulkapedia.compose.ui.theme.BulkaPediaShapes
import com.bulkapedia.compose.ui.theme.roundedShapes

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Teal200,
    background = Primary
)

@Composable
fun BulkaPediaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        content = content
    )
}

object BulkaPediaTheme {

    val colors: BulkaPediaColors
        @Composable
        get() = LocalBulkaPediaColors.current

    val shapes: BulkaPediaShapes
        @Composable
        get() = LocalBulkaPediaShapes.current

}

private object NoRipple : RippleTheme {

    @Composable
    override fun defaultColor(): Color = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)

}

val LocalBulkaPediaColors = staticCompositionLocalOf<BulkaPediaColors> {
    error("")
}

val LocalBulkaPediaShapes = staticCompositionLocalOf<BulkaPediaShapes> {
    error("")
}

@Composable
fun MainTheme(
    colors: BulkaPediaColors = basePalette,
    shapes: BulkaPediaShapes = roundedShapes,
    rippleTheme: RippleTheme = NoRipple,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalBulkaPediaColors provides colors,
        LocalBulkaPediaShapes provides shapes,
        LocalRippleTheme provides rippleTheme,
        content = content
    )
}
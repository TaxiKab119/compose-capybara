package app.alexbalan.composecapybara.core.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration

object TextStyles {
    @Composable
    fun conceptLinkStyle() = TextLinkStyles(
        style = SpanStyle(
            color = AppColors.levelText,
            fontFamily = FontFamily.Monospace,
            textDecoration = TextDecoration.Underline,
            background = MaterialTheme.colors.primaryVariant.copy(alpha = 0.1f)
        ),
        hoveredStyle = SpanStyle(
            color = AppColors.levelText,
            fontFamily = FontFamily.Monospace,
            textDecoration = TextDecoration.Underline,
            background = MaterialTheme.colors.primaryVariant.copy(alpha = 0.2f)
        )
    )

    @Composable
    fun conceptLinkTextStyle() = TextStyle(
        color = AppColors.levelText,
        fontFamily = FontFamily.Monospace,
        textDecoration = TextDecoration.Underline,
        background = MaterialTheme.colors.primaryVariant.copy(alpha = 0.1f)
    )

    @Composable
    fun nonClickableHighlightStyle() = SpanStyle(
        color = AppColors.levelText,
        fontFamily = FontFamily.Monospace,
        background = MaterialTheme.colors.primaryVariant.copy(alpha = 0.1f)
    )
}
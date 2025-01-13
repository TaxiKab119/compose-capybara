package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import app.alexbalan.composecapybara.core.data.stage.CushionType
import composecapybara.composeapp.generated.resources.Res
import composecapybara.composeapp.generated.resources.bara_blue_sleepy
import composecapybara.composeapp.generated.resources.bara_orange_sleepy
import composecapybara.composeapp.generated.resources.bara_purple_sleepy
import composecapybara.composeapp.generated.resources.blue_capybara
import composecapybara.composeapp.generated.resources.orange_capybara
import composecapybara.composeapp.generated.resources.purple_capybara
import org.jetbrains.compose.resources.vectorResource

@Composable
fun Capybara(
    cushionType: CushionType,
    isSleepy: Boolean = false,
    modifier: Modifier = Modifier,
) {
    // breathing animation
    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .size(128.dp)
            .padding(12.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        contentAlignment = Alignment.Center
    ) {
        val capyRes = when (cushionType) {
            CushionType.BLUE -> if (isSleepy) Res.drawable.bara_blue_sleepy else Res.drawable.blue_capybara
            CushionType.ORANGE -> if (isSleepy) Res.drawable.bara_orange_sleepy else Res.drawable.orange_capybara
            CushionType.PURPLE -> if (isSleepy) Res.drawable.bara_purple_sleepy else Res.drawable.purple_capybara
        }
        Image(
            imageVector = vectorResource(capyRes),
            contentDescription = "Capybara",
        )
    }
}
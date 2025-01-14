package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.alexbalan.composecapybara.core.data.stage.CushionType
import composecapybara.composeapp.generated.resources.Res
import composecapybara.composeapp.generated.resources.blue_cushion
import composecapybara.composeapp.generated.resources.blue_cushion_colourblind
import composecapybara.composeapp.generated.resources.orange_cushion
import composecapybara.composeapp.generated.resources.orange_cushion_colourblind
import composecapybara.composeapp.generated.resources.purple_cushion
import org.jetbrains.compose.resources.vectorResource

@Composable
fun Cushion(
    cushionType: CushionType,
    isColorblind: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(128.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        val resId = when (cushionType) {
            CushionType.BLUE -> if (isColorblind) Res.drawable.blue_cushion_colourblind else Res.drawable.blue_cushion
            CushionType.ORANGE -> if (isColorblind) Res.drawable.orange_cushion_colourblind else Res.drawable.orange_cushion
            CushionType.PURPLE -> if (isColorblind) Res.drawable.purple_cushion else Res.drawable.purple_cushion
        }
        Image(
            imageVector = vectorResource(resId),
            contentDescription = cushionType.name
        )
    }
}
package app.alexbalan.composecapybara.core.data.stage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment

sealed class UiContainer {
    data class Box(
        val contentAlignment: Alignment = Alignment.TopStart
    ) : UiContainer()

    data class Row(
        val verticalAlignment: Alignment.Vertical = Alignment.Top,
        val horizontalArrangement: Arrangement.Horizontal = Arrangement.Start
    ) : UiContainer()

    data class Column(
        val horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        val verticalArrangement: Arrangement.Vertical = Arrangement.Top
    ) : UiContainer()
}
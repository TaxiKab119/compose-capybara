package app.alexbalan.composecapybara.core.data.stage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment

sealed class UiContainer {
    data class Box(
        val contentAlignment: Alignment? = null
    ) : UiContainer()

    data class Row(
        val verticalAlignment: Alignment.Vertical? = null,
        val horizontalArrangement: Arrangement.Horizontal? = null
    ) : UiContainer()

    data class Column(
        val horizontalAlignment: Alignment.Horizontal? = null,
        val verticalArrangement: Arrangement.Vertical? = null
    ) : UiContainer()
}
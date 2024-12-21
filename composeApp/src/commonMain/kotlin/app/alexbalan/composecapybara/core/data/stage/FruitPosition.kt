package app.alexbalan.composecapybara.core.data.stage

import androidx.compose.ui.Alignment

sealed class FruitPosition {
    data class InBox(val alignment: Alignment? = null) : FruitPosition()
    data class InRow(val alignment: Alignment.Vertical? = null) : FruitPosition()
    data class InColumn(val alignment: Alignment.Horizontal? = null) : FruitPosition()
}
package app.alexbalan.composecapybara.core.data.stage

import androidx.compose.ui.Alignment

sealed class FruitPosition {
    data class InBox(val fruitType: FruitType, val alignment: Alignment? = null) : FruitPosition()
    data class InRow(val fruitType: FruitType, val alignment: Alignment.Vertical? = null) : FruitPosition()
    data class InColumn(val fruitType: FruitType, val alignment: Alignment.Horizontal? = null) : FruitPosition()
}

enum class FruitType {
    BLUEBERRY, CARROT, GRAPE, STRAWBERRY
}
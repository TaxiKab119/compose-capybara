package app.alexbalan.composecapybara.core.data.stage

import androidx.compose.ui.Alignment


/**
 * Element position defines the type and additional alignment of UI elements
 * including Capybaras (user-controlled) and fruits (static stage elements).
 *
 * @constructor Create empty Fruit position
 */
sealed class ElementPosition {
    data class InBox(val fruitType: FruitType, val alignment: Alignment? = null) : ElementPosition()
    data class InRow(val fruitType: FruitType, val alignment: Alignment.Vertical? = null) : ElementPosition()
    data class InColumn(val fruitType: FruitType, val alignment: Alignment.Horizontal? = null) : ElementPosition()
}

enum class FruitType {
    BLUEBERRY, CARROT, GRAPE, STRAWBERRY
}
package app.alexbalan.composecapybara.core.presentation.util

import androidx.compose.ui.Modifier

fun Modifier.applyIfElse(
    condition: Boolean,
    modIfTrue: Modifier,
    modIfFalse: Modifier = Modifier
): Modifier = this.then(if (condition) modIfTrue else modIfFalse)
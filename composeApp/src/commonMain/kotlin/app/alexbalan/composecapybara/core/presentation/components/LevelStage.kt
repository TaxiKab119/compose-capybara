package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.alexbalan.composecapybara.core.data.stage.CushionType
import app.alexbalan.composecapybara.core.data.stage.ElementPosition
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer
import app.alexbalan.composecapybara.core.presentation.util.applyIfElse

sealed interface StageElement {
    data class Cushion(
        val element: @Composable (cushionType: CushionType, modifier: Modifier) -> Unit
    ) : StageElement

    data class Capybara(
        val element: @Composable (cushionType: CushionType, modifier: Modifier, isCorrect: Boolean) -> Unit
    ) : StageElement
}

@Composable
fun LevelStageRoot(
    stageLayout: StageLayout?,
    levelNumber: Int,
    isCorrect: Boolean,
    stageElement: StageElement,
    modifier: Modifier = Modifier,
) {
    if (stageLayout == null) {
        CustomLevelStage(
            levelNumber = levelNumber,
            modifier = modifier
        )
    } else {
        ConfigDrivenUiStage(
            stageLayout = stageLayout,
            modifier = modifier,
            isCorrect = isCorrect,
            stageElement = stageElement
        )
    }
}

@Composable
fun ConfigDrivenUiStage(
    stageLayout: StageLayout,
    isCorrect: Boolean,
    stageElement: StageElement,
    modifier: Modifier = Modifier,
) {
    when (stageLayout.container) {
        is UiContainer.Box -> {
            Box(
                modifier = modifier,
                contentAlignment = stageLayout.container.contentAlignment ?: Alignment.TopStart
            ) {
                stageLayout.elements.forEach { elementPosition ->
                    when (elementPosition) {
                        is ElementPosition.InBox -> {
                            StageElement(
                                stageElement = stageElement,
                                modifier = Modifier
                                    .applyIfElse(
                                        condition = elementPosition.alignment != null,
                                        modIfTrue = Modifier.align(elementPosition.alignment ?: Alignment.TopStart)
                                    )
                                    .then(elementPosition.modifier),
                                cushionType = elementPosition.cushionType,
                                isCorrect = isCorrect
                            )
                        }
                        else -> error("Container is Box but ElementPosition is in $elementPosition")
                    }
                }
            }
        }

        is UiContainer.Column -> {
            Column(
                modifier = modifier,
                horizontalAlignment = stageLayout.container.horizontalAlignment ?: Alignment.Start,
                verticalArrangement = stageLayout.container.verticalArrangement ?: Arrangement.Top
            ) {
                stageLayout.elements.forEach { elementPosition ->
                    when(elementPosition) {
                        is ElementPosition.InColumn -> {
                            StageElement(
                                stageElement = stageElement,
                                modifier = Modifier
                                    .applyIfElse(
                                        condition = elementPosition.alignment != null,
                                        modIfTrue = Modifier.align(elementPosition.alignment ?: Alignment.Start)
                                    )
                                    .then(elementPosition.modifier),
                                cushionType = elementPosition.cushionType,
                                isCorrect = isCorrect
                            )
                        }
                        else -> error("Container is Column but ElementPosition is in $elementPosition")
                    }
                }
            }
        }

        is UiContainer.Row -> {
            Row(
                modifier = modifier,
                verticalAlignment = stageLayout.container.verticalAlignment ?: Alignment.Top,
                horizontalArrangement = stageLayout.container.horizontalArrangement ?: Arrangement.Start
            ) {
                stageLayout.elements.forEach { elementPosition ->
                    when(elementPosition) {
                        is ElementPosition.InRow -> {
                            StageElement(
                                stageElement = stageElement,
                                modifier = Modifier
                                    .applyIfElse(
                                        condition = elementPosition.alignment != null,
                                        modIfTrue = Modifier.align(elementPosition.alignment ?: Alignment.Top)
                                    )
                                    .then(elementPosition.modifier),
                                cushionType = elementPosition.cushionType,
                                isCorrect = isCorrect
                            )
                        }
                        else -> error("Container is Row but ElementPosition is in $elementPosition")
                    }
                }
            }
        }
    }
}

@Composable
private fun StageElement(
    stageElement: StageElement,
    cushionType: CushionType,
    isCorrect: Boolean,
    modifier: Modifier = Modifier,
) {
    when (stageElement) {
        is StageElement.Cushion -> stageElement.element(cushionType, modifier)
        is StageElement.Capybara -> stageElement.element(cushionType, modifier, isCorrect)
    }
}


@Composable
fun CustomLevelStage(
    levelNumber: Int,
    modifier: Modifier = Modifier
) {
    when(levelNumber) {
        4 -> Level4StageCustom(modifier)
        else -> LoadingScreen(modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun Level4StageCustom(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF9A8468))
    ) {
        Text("This is a custom stage")
    }
}
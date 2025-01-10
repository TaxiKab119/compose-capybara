package app.alexbalan.composecapybara.core.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.alexbalan.composecapybara.core.data.stage.ElementPosition
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer
import app.alexbalan.composecapybara.core.presentation.Capybara
import app.alexbalan.composecapybara.core.presentation.util.applyIfElse


@Composable
fun DynamicLevelStageRoot(
    stageLayout: StageLayout?,
    levelNumber: Int,
    modifier: Modifier = Modifier
) {
    if (stageLayout == null) {
        DynamicCustomLevelStage(
            levelNumber = levelNumber,
            modifier = modifier
        )
    } else {
        RepoDrivenDynamicLevelStage(
            stageLayout = stageLayout,
            modifier = modifier
        )
    }
}

@Composable
fun DynamicCustomLevelStage(
    levelNumber: Int,
    modifier: Modifier = Modifier
) {
    when(levelNumber) {
        4 -> Level4StageCustomDynamic(modifier)
        else -> LoadingScreen()
    }
}


@Composable
fun Level4StageCustomDynamic(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text("This is a custom stage")
    }
}

@Composable
fun RepoDrivenDynamicLevelStage(
    stageLayout: StageLayout,
    modifier: Modifier = Modifier
) {

    when (stageLayout.container) {
        is UiContainer.Box -> {
            Box(
                modifier = modifier,
                contentAlignment = stageLayout.container.contentAlignment ?: Alignment.TopStart
            ) {
                stageLayout.elements.forEach { fruitPosition ->
                    when (fruitPosition) {
                        is ElementPosition.InBox -> {
                            Capybara(
                                modifier = fruitPosition.modifier,
                                cushionType = fruitPosition.cushionType
                            )
                        }
                        else -> error("Container is Box but FruitPosition is in $fruitPosition")
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
                stageLayout.elements.forEach { fruitPosition ->
                    when(fruitPosition) {
                        is ElementPosition.InColumn -> {
                            Capybara(
                                modifier = Modifier
                                    .applyIfElse(
                                        condition = fruitPosition.alignment != null,
                                        modIfTrue = Modifier.align(fruitPosition.alignment ?: Alignment.Start)
                                    ),
                                cushionType = fruitPosition.cushionType
                            )
                        }
                        else -> error("Container is Box but FruitPosition is in $fruitPosition")
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
                stageLayout.elements.forEach { fruitPosition ->
                    when(fruitPosition) {
                        is ElementPosition.InRow -> {
                            Capybara(
                                modifier = Modifier
                                    .applyIfElse(
                                        condition = fruitPosition.alignment != null,
                                        modIfTrue = Modifier.align(fruitPosition.alignment ?: Alignment.Top)
                                    ),
                                cushionType = fruitPosition.cushionType
                            )
                        }
                        else -> error("Container is Box but FruitPosition is in $fruitPosition")
                    }
                }
            }
        }
    }
}
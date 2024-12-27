package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.foundation.background
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
import app.alexbalan.composecapybara.core.data.stage.FruitPosition
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer
import app.alexbalan.composecapybara.core.presentation.Fruit
import app.alexbalan.composecapybara.core.presentation.util.applyIfElse


@Composable
fun LevelStageRoot(
    stageLayout: StageLayout?,
    levelNumber: Int,
    modifier: Modifier = Modifier
) {
    if (stageLayout == null) {
        CustomLevelStage(
            levelNumber = levelNumber,
            modifier = modifier
        )
    } else {
        RepoDrivenLevelStage(
            stageLayout = stageLayout,
            modifier = modifier
        )
    }
}

@Composable
fun CustomLevelStage(
    levelNumber: Int,
    modifier: Modifier = Modifier
) {
    when(levelNumber) {
        4 -> Level4StageCustom(modifier)
        else -> LoadingScreen()
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF9A8468))
    ) {
        CircularProgressIndicator()
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

@Composable
fun RepoDrivenLevelStage(
    stageLayout: StageLayout,
    modifier: Modifier = Modifier
) {
    val lightBrown = Color(0xFF9A8468)

    when (stageLayout.container) {
        is UiContainer.Box -> {
            Box(
                modifier = modifier
                    .background(color = lightBrown)
                    .fillMaxSize(),
            ) {
                stageLayout.fruit.forEach { fruitPosition ->
                    when (fruitPosition) {
                        is FruitPosition.InBox -> {
                            Fruit(
                                modifier = Modifier
                                    .applyIfElse(
                                        condition = fruitPosition.alignment != null,
                                        modIfTrue = Modifier.align(fruitPosition.alignment ?: Alignment.TopStart)
                                    ),
                                fruitType = fruitPosition.fruitType
                            )
                        }
                        else -> error("Container is Box but FruitPosition is in $fruitPosition")
                    }
                }
            }
        }

        is UiContainer.Column -> {
            Column(
                modifier = modifier
                    .background(color = lightBrown)
                    .fillMaxSize(),
                horizontalAlignment = stageLayout.container.horizontalAlignment,
                verticalArrangement = stageLayout.container.verticalArrangement
            ) {
                stageLayout.fruit.forEach { fruitPosition ->
                    when(fruitPosition) {
                        is FruitPosition.InColumn -> {
                            Fruit(
                                modifier = Modifier
                                    .applyIfElse(
                                        condition = fruitPosition.alignment != null,
                                        modIfTrue = Modifier.align(fruitPosition.alignment ?: Alignment.Start)
                                    ),
                                fruitType = fruitPosition.fruitType
                            )
                        }
                        else -> error("Container is Box but FruitPosition is in $fruitPosition")
                    }
                }
            }
        }

        is UiContainer.Row -> {
            Row(
                modifier = modifier
                    .background(color = lightBrown)
                    .fillMaxSize(),
                verticalAlignment = stageLayout.container.verticalAlignment,
                horizontalArrangement = stageLayout.container.horizontalArrangement
            ) {
                stageLayout.fruit.forEach { fruitPosition ->
                    when(fruitPosition) {
                        is FruitPosition.InRow -> {
                            Fruit(
                                modifier = Modifier
                                    .applyIfElse(
                                        condition = fruitPosition.alignment != null,
                                        modIfTrue = Modifier.align(fruitPosition.alignment ?: Alignment.Top)
                                    ),
                                fruitType = fruitPosition.fruitType
                            )
                        }
                        else -> error("Container is Box but FruitPosition is in $fruitPosition")
                    }
                }
            }
        }
    }
}
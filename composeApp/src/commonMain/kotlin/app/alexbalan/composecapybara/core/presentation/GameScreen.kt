package app.alexbalan.composecapybara.core.presentation

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.alexbalan.composecapybara.core.data.stage.FruitType
import app.alexbalan.composecapybara.core.presentation.components.CodeField
import app.alexbalan.composecapybara.core.presentation.components.DynamicLevelStageRoot
import app.alexbalan.composecapybara.core.presentation.components.LevelStageRoot
import composecapybara.composeapp.generated.resources.Res
import composecapybara.composeapp.generated.resources.blueberry
import composecapybara.composeapp.generated.resources.carrot
import composecapybara.composeapp.generated.resources.caterpillar
import composecapybara.composeapp.generated.resources.grape
import composecapybara.composeapp.generated.resources.strawberry
import org.jetbrains.compose.resources.vectorResource


@Composable
fun GameScreenRoot(
    viewModel: LevelViewModel,
    onForwardClick: (Int) -> Unit,
    onBackwardClick: (Int) -> Unit,
    onTextUpdated: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GameScreen(
        onForwardClick = { onForwardClick(it) },
        onBackwardClick = { onBackwardClick(it) },
        onTextUpdated = { onTextUpdated(it) },
        uiState = uiState
    )
}

@Composable
fun GameScreen(
    uiState: LevelScreenUiState,
    onForwardClick: (Int) -> Unit,
    onBackwardClick: (Int) -> Unit,
    onTextUpdated: (String) -> Unit,
) {
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Compose Capybara",
                        style = MaterialTheme.typography.h4
                    )
                    LevelSelector(
                        onForwardClick = { onForwardClick(it) },
                        onBackwardClick = { onBackwardClick(it) },
                        levelNumber = uiState.levelNumber
                    )
                }
                LevelPreamble(uiState.preamble)
                Spacer(Modifier.height(8.dp))
                LevelInstructions(uiState.instructions)
                Spacer(Modifier.height(8.dp))
                CodeField(
                    Modifier.align(Alignment.CenterHorizontally),
                    userInput = uiState.userInput,
                    onTextUpdated = onTextUpdated,
                    existingLinesBefore = uiState.existingLinesBefore,
                    existingLinesAfter = uiState.existingLinesAfter,
                    numUserInputLines = uiState.numUserInputLines,
                    onNextClicked = { onForwardClick(uiState.levelNumber) },
                    isCorrect = uiState.showCorrect
                )
            }
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                val lightBrown = Color(0xFF9A8468) // TODO - Extract into Theme
                // Static Stage elements
                LevelStageRoot(
                    levelNumber = uiState.levelNumber,
                    stageLayout = uiState.fruitStageLayout,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(lightBrown)
                )

                // Dynamic gameplay layer (user-controlled)
                DynamicLevelStageRoot(
                    levelNumber = uiState.levelNumber,
                    stageLayout = uiState.capybaraStageLayout,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }

}

@Composable
fun LevelSelector(
    modifier: Modifier = Modifier,
    onBackwardClick: (Int) -> Unit,
    onForwardClick: (Int) -> Unit,
    levelNumber: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(
            onClick = { onBackwardClick(levelNumber) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        Text(text = "Level $levelNumber")
        IconButton(
            onClick = { onForwardClick(levelNumber) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "Next"
            )
        }

    }
}

@Composable
fun LevelPreamble(
    preamble: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = preamble,
        modifier = modifier
    )
}


@Composable
fun LevelInstructions(
    instructions: List<String>,
    modifier: Modifier = Modifier
) {
    val bullet = "\u2022"
    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
    Text(
        text = buildAnnotatedString {
            instructions.forEach {
                withStyle(style = paragraphStyle) {
                    append("    ")
                    append(bullet)
                    append("  ")
                    append(it)
                }
            }
        },
    )
}

@Composable
fun Capybara(
    modifier: Modifier = Modifier,
    fruitType: FruitType
) {
    // breathing animation
    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(128.dp)
            .padding(12.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        contentAlignment = Alignment.Center
    ) {
        val colorFilter = when (fruitType) {
            FruitType.BLUEBERRY -> ColorFilter.tint(Color.Blue)
            FruitType.CARROT -> ColorFilter.tint(Color.Yellow)
            FruitType.GRAPE -> ColorFilter.tint(Color.Magenta)
            FruitType.STRAWBERRY -> ColorFilter.tint(Color.Red)
        }
        Image(
            imageVector = vectorResource(Res.drawable.caterpillar),
            contentDescription = "Caterpillar",
            colorFilter = colorFilter
        )
    }
}

@Composable
fun Fruit(
    modifier: Modifier = Modifier,
    fruitType: FruitType,
) {
    Box(
        modifier = modifier
            .size(128.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {

        val resId = when (fruitType) {
            FruitType.BLUEBERRY -> Res.drawable.blueberry
            FruitType.CARROT -> Res.drawable.carrot
            FruitType.GRAPE -> Res.drawable.grape
            FruitType.STRAWBERRY -> Res.drawable.strawberry
        }
        Image(
            imageVector = vectorResource(resId),
            contentDescription = fruitType.name
        )
    }
}
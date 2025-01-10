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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.alexbalan.composecapybara.core.data.stage.CushionType
import app.alexbalan.composecapybara.core.presentation.components.CodeField
import app.alexbalan.composecapybara.core.presentation.components.DynamicLevelStageRoot
import app.alexbalan.composecapybara.core.presentation.components.LevelStageRoot
import app.alexbalan.composecapybara.core.presentation.components.TwoInputCodeField
import composecapybara.composeapp.generated.resources.Res
import composecapybara.composeapp.generated.resources.bara_blue_sleepy
import composecapybara.composeapp.generated.resources.bara_orange_sleepy
import composecapybara.composeapp.generated.resources.bara_purple_sleepy
import composecapybara.composeapp.generated.resources.blue_capybara
import composecapybara.composeapp.generated.resources.blue_cushion
import composecapybara.composeapp.generated.resources.orange_capybara
import composecapybara.composeapp.generated.resources.orange_cushion
import composecapybara.composeapp.generated.resources.purple_capybara
import composecapybara.composeapp.generated.resources.purple_cushion
import org.jetbrains.compose.resources.vectorResource


@Composable
fun GameScreenRoot(
    viewModel: LevelViewModel,
    onForwardClick: (Int) -> Unit,
    onBackwardClick: (Int) -> Unit,
    onTextUpdated: (String) -> Unit,
    onText2Updated: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GameScreen(
        onForwardClick = { onForwardClick(it) },
        onBackwardClick = { onBackwardClick(it) },
        onTextUpdated = { onTextUpdated(it) },
        onText2Updated = { onText2Updated(it) },
        uiState = uiState
    )
}

@Composable
fun GameScreen(
    uiState: LevelScreenUiState,
    onForwardClick: (Int) -> Unit,
    onBackwardClick: (Int) -> Unit,
    onTextUpdated: (String) -> Unit,
    onText2Updated: (String) -> Unit
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
                when(uiState.numUserInputLines) {
                    1 -> {
                        CodeField(
                            Modifier.align(Alignment.CenterHorizontally),
                            userInput = uiState.codeFieldState1.userInput,
                            onTextUpdated = onTextUpdated,
                            existingLinesBefore = uiState.codeFieldState1.existingLinesBefore,
                            existingLinesAfter = uiState.codeFieldState1.existingLinesAfter,
                            numUserInputLines = uiState.codeFieldState1.numUserInputLines,
                            onNextClicked = { onForwardClick(uiState.levelNumber) },
                            isCorrect = uiState.showCorrect
                        )
                    }
                    2 -> {
                        TwoInputCodeField(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            codeFieldStateState1 = uiState.codeFieldState1,
                            codeFieldStateState2 = uiState.codeFieldState2!!,
                            isCorrect = uiState.showCorrect,
                            onTextUpdated1 = onTextUpdated,
                            onTextUpdated2 = onText2Updated,
                            onNextClicked = { onForwardClick(uiState.levelNumber) }
                        )
                    }
                }
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
                        .padding(12.dp),
                    isCorrect = uiState.showCorrect
                )

                // Dynamic gameplay layer (user-controlled)
                DynamicLevelStageRoot(
                    levelNumber = uiState.levelNumber,
                    stageLayout = uiState.capybaraStageLayout,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    isCorrect = uiState.showCorrect
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
    cushionType: CushionType,
    isSleepy: Boolean = false,
    modifier: Modifier = Modifier,
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
        modifier = modifier
            .size(128.dp)
            .padding(12.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        contentAlignment = Alignment.Center
    ) {
        val capyRes = when (cushionType) {
            CushionType.BLUE -> if (isSleepy) Res.drawable.bara_blue_sleepy else Res.drawable.blue_capybara
            CushionType.ORANGE -> if (isSleepy) Res.drawable.bara_orange_sleepy else Res.drawable.orange_capybara
            CushionType.PURPLE -> if (isSleepy) Res.drawable.bara_purple_sleepy else Res.drawable.purple_capybara
        }
        Image(
            imageVector = vectorResource(capyRes),
            contentDescription = "Capybara",
        )
    }
}

@Composable
fun Cushion(
    cushionType: CushionType,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(128.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {

        val resId = when (cushionType) {
            CushionType.BLUE -> Res.drawable.blue_cushion
            CushionType.ORANGE -> Res.drawable.orange_cushion
            CushionType.PURPLE -> Res.drawable.purple_cushion
        }
        Image(
            imageVector = vectorResource(resId),
            contentDescription = cushionType.name
        )
    }
}
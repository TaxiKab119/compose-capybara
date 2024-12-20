package app.alexbalan.composecapybara.core.presentation

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
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.alexbalan.composecapybara.core.presentation.components.CodeField
import composecapybara.composeapp.generated.resources.Res
import composecapybara.composeapp.generated.resources.caterpillar
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
                        text = "Compose Caterpillar",
                        style = MaterialTheme.typography.h4
                    )
                    LevelSelector(
                        onForwardClick = { onForwardClick(it) },
                        onBackwardClick = { onBackwardClick(it) },
                        levelNumber = uiState.levelNumber
                    )
                }
                LevelPreamble("Preamble: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sed erat congue, commodo urna sit amet, venenatis ex. Phasellus egestas, urna ac accumsan aliquet, tortor libero posuere magna, eu tempus neque nisl sit amet nisl. Donec eget aliquam tellus. Aliquam sit amet vehicula tellus. Nullam vel purus vitae nunc semper lobortis eget id turpis. Suspendisse vitae quam at massa venenatis fringilla. Phasellus lobortis ex non lorem sagittis, sed laoreet lacus mollis.")
                Spacer(Modifier.height(8.dp))
                LevelInstructions(listOf("This is the first instruction", "This is the second instruction", "Here is a third instruction"))
                Spacer(Modifier.height(8.dp))
                CodeField(
                    Modifier.align(Alignment.CenterHorizontally),
                    userInput = uiState.userInput,
                    onTextUpdated = onTextUpdated,
                    existingLinesBefore = uiState.existingLinesBefore,
                    existingLinesAfter = uiState.existingLinesAfter,
                    numUserInputLines = uiState.numUserInputLines,
                    onNextClicked = { onForwardClick(uiState.levelNumber) }
                )
                if (uiState.showCorrect) {
                    Text("You got it correct!")
                }
            }
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                // Caterpillars and Leaves
                LevelStage(Modifier.fillMaxSize())

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.End
                ) {
                    Caterpillar()
                }
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
                    append("\t")
                    append(bullet)
                    append("\t")
                    append(it)
                }
            }
        },
    )
}

@Composable
fun LevelStage(modifier: Modifier = Modifier) {
    val lightBrown = Color(0xFF9A8468)
    Column(
        modifier = modifier
            .background(color = lightBrown),
        horizontalAlignment = Alignment.End
    ) {
        Leaf(tint = Color.Green)
    }
}

@Composable
fun Caterpillar(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(128.dp)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = vectorResource(Res.drawable.caterpillar),
            contentDescription = "Caterpillar"
        )
    }
}

@Composable
fun Leaf(
    modifier: Modifier = Modifier,
    tint: Color
) {
    Box(
        modifier = Modifier
            .size(128.dp)
            .padding(4.dp)
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
    }
}
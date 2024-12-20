package app.alexbalan.composecaterpillar.previews

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.alexbalan.composecapybara.core.presentation.GameScreen
import app.alexbalan.composecapybara.core.presentation.LevelScreenUiState


@Preview(device = "spec:width=1920dp,height=1080dp,dpi=160")
@Composable
private fun GameScreenPreview() {
    val sampleUiState = LevelScreenUiState(
        levelNumber = 0,
        userInput = "verticalAlignment = Alignment.End",
        numUserInputLines = 1,
        existingLinesBefore = listOf("Column ("),
        existingLinesAfter = listOf("\t\tmodifier = modifier", "\t\t\t\t.background(Color.Green)", "\t\t\t\t.fillMaxSize()", ") {", "   \tCaterpillar()", "}"),
    )

    MaterialTheme {
        GameScreen(
            uiState = sampleUiState,
            onBackwardClick = {},
            onForwardClick = {},
            onTextUpdated = {}
        )
    }

}
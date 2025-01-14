package app.alexbalan.composecapybara.previews

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
        numUserInputLines = 1,
    )

    MaterialTheme {
        GameScreen(
            uiState = sampleUiState,
            onBackwardClick = {},
            onForwardClick = {},
            onTextUpdated1 = {},
            onTextUpdated2 = {},
            onTextUpdated3 = {},
            onTextUpdated4 = {},
            onLevelSelected = {},
            onColorBlindToggled = {},
            onDifficultyChanged = {},
            onResetProgress = {}
        )
        
    }

}
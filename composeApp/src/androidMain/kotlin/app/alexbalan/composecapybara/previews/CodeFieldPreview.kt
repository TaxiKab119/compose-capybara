package app.alexbalan.composecapybara.previews

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.alexbalan.composecapybara.core.presentation.components.CodeField

@Preview
@Composable
private fun CodeFieldPreview() {
    MaterialTheme {
        CodeField(
            userInput = "",
            existingLinesBefore = listOf("Column ("),
            existingLinesAfter = listOf("\t\tmodifier = modifier", "\t\t\t\t.background(Color.Green)", "\t\t\t\t.fillMaxSize()", ") {", "   \tCaterpillar()", "}"),
            numUserInputLines = 1,
            onTextUpdated = {},
            onNextClicked = {}
        )
    }
}
package app.alexbalan.composecapybara.core.presentation

import app.alexbalan.composecapybara.core.data.stage.StageLayout

data class LevelScreenUiState(
    val levelNumber: Int,
    val showCorrect: Boolean = false,

    val preamble: String = "",
    val instructions: List<String> = listOf(),
    val hints: List<String> = listOf(),

    // Code Field UI
    val userInput: String = "",
    val existingLinesBefore: List<String> = listOf(),
    val existingLinesAfter: List<String> = listOf(),
    val numUserInputLines: Int = 1,
    val validInput: Set<String> = setOf(),
    val correctAnswer: String = "1q2w3e4r5t",

    // Layout
    val stageLayout: StageLayout? = null
)

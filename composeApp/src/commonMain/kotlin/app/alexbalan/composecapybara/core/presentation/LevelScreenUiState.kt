package app.alexbalan.composecapybara.core.presentation

import app.alexbalan.composecapybara.core.data.AnswerType
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer

// TODO - Replace all these initial values with a loading state
data class LevelScreenUiState(
    val levelNumber: Int,
    val showCorrect: Boolean = false,
    val correctContainer: UiContainer = UiContainer.Column(),

    val preamble: String = "",
    val instructions: List<String> = listOf(),
    val hints: List<String> = listOf(),

    // Code Field UI
    val numUserInputLines: Int = 1,
    val codeFieldState1: CodeFieldState = CodeFieldState(),
    val codeFieldState2: CodeFieldState? = null,
    val codeFieldState3: CodeFieldState? = null,
    val codeFieldState4: CodeFieldState? = null,

    // Layout
    val fruitStageLayout: StageLayout? = null,
    val capybaraStageLayout: StageLayout? = null
)

data class CodeFieldState(
    val userInput: String = "",
    val numUserInputLines: Int = 1,
    val validInput: Set<String> = setOf(),
    val existingLinesBefore: List<String> = listOf(),
    val existingLinesAfter: List<String> = listOf(),
    val answerType: AnswerType = AnswerType.COLUMN,
    val isCorrect: Boolean = false,
)

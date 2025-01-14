package app.alexbalan.composecapybara.core.data.levels

import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.presentation.CodeFieldState

data class LevelConfig(
    // Preamble, Instructions, Hints
    val preamble: String = "",
    val instructions: List<String> = listOf(),
    val hints: List<String> = listOf(),

    // Code Field
    val codeFieldState1: CodeFieldState = CodeFieldState(),
    val codeFieldState2: CodeFieldState? = null,
    val codeFieldState3: CodeFieldState? = null,
    val codeFieldState4: CodeFieldState? = null,


    // Stage Layout - if null, custom layout is used for the level instead of the repo-driven stageLayout
    val stageLayout: StageLayout? = null,
    val initialUserStageLayout: StageLayout? = null
)

enum class AnswerType {
    COLUMN, ROW, BOX, COL_ALIGN, ROW_ALIGN, BOX_ALIGN
}





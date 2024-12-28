package app.alexbalan.composecapybara.core.data

import app.alexbalan.composecapybara.core.data.stage.StageLayout

data class LevelConfig(
    // Preamble, Instructions, Hints
    val preamble: String,
    val instructions: List<String> = listOf(),
    val hints: List<String> = listOf(),

    // Code Field
    val initialUserInput: String,
    val numUserInputLines: Int,
    val existingLinesBefore: List<String>,
    val existingLinesAfter: List<String>,
    val validInput: Set<String>,
    val correctAnswer: String,
    val answerType: AnswerType, // dictates what set of answers will modify UI (i.e., move capybara)

    // Stage Layout - if null, custom layout is used for the level instead of the repo-driven stageLayout
    val stageLayout: StageLayout? = null,
    val initialUserStageLayout: StageLayout? = null
)

enum class AnswerType {
    COLUMN, ROW, BOX, COL_ALIGN, ROW_ALIGN, BOX_ALIGN
}





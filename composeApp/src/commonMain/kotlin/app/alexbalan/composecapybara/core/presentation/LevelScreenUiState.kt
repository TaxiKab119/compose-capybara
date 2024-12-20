package app.alexbalan.composecapybara.core.presentation

data class LevelScreenUiState(
    val levelNumber: Int,
    val showCorrect: Boolean = false,

    // Code Field UI
    val userInput: String = "",
    val existingLinesBefore: List<String> = listOf(),
    val existingLinesAfter: List<String> = listOf(),
    val numUserInputLines: Int = 1,

)

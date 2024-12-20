package app.alexbalan.composecapybara.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LevelViewModel(
    levelNumber: Int
): ViewModel() {

    private val _uiState = MutableStateFlow(
        LevelScreenUiState(
            levelNumber = levelNumber,
            userInput = "verticalAlignment = Alignment.End",
            numUserInputLines = 1,
            existingLinesBefore = listOf("Column ("),
            existingLinesAfter = listOf("\t\tmodifier = modifier", "\t\t\t\t.background(Color.Green)", "\t\t\t\t.fillMaxSize()", ") {", "   \tCaterpillar()", "}")
        )
    )
    val uiState = _uiState.asStateFlow()


    fun updateUserInput(userInput: String) {
        _uiState.update {
            it.copy(
                userInput = userInput
            )
        }
        isInputCorrectJob?.cancel()
        isInputCorrectJob = viewModelScope.launch {
            delay(500)
            isUserInputCorrect(userInput)
        }
    }

    private var isInputCorrectJob: Job? = null
    private fun isUserInputCorrect(userInput: String) {
        when(userInput) {
            "horizontalAlignment = Alignment.End" -> {
                _uiState.update { it.copy( showCorrect = true) }
            }
        }
    }

}
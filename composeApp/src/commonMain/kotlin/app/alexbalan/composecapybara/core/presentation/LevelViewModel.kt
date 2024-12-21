package app.alexbalan.composecapybara.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.alexbalan.composecapybara.core.data.LevelRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LevelViewModel(
    private val levelNumber: Int,
    private val levelRepository: LevelRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LevelScreenUiState(levelNumber))
    val uiState = _uiState.asStateFlow()
        .onStart { loadLevel() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            LevelScreenUiState(levelNumber)
        )


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

    private fun loadLevel() {
        val levelConfig = levelRepository.getLevelConfig(levelNumber)

        _uiState.update {
            it.copy(
                preamble = levelConfig.preamble,
                instructions = levelConfig.instructions,
                hints = levelConfig.hints,

                // Code Field UI
                existingLinesBefore = levelConfig.existingLinesBefore,
                existingLinesAfter = levelConfig.existingLinesAfter,
                numUserInputLines = levelConfig.numUserInputLines,
                validInput = levelConfig.validInput,
                correctAnswer = levelConfig.correctAnswer,
            )
        }
    }

}
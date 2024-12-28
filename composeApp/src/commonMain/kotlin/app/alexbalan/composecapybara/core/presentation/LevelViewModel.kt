package app.alexbalan.composecapybara.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.alexbalan.composecapybara.core.data.AnswerType
import app.alexbalan.composecapybara.core.data.LevelRepository
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer
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

    // Memoized validation data
    private var correctAnswer: String = ""
    private var validInputs: Set<String> = emptySet()
    private var initialCapyPosition: StageLayout? = null

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
            correctAnswer -> {
                _uiState.update { it.copy( showCorrect = true) }
            }
            else -> {
                _uiState.update { it.copy( showCorrect = false) }
                if (!validInputs.contains(userInput)) {
                    _uiState.update { it.copy(
                        capybaraStageLayout = initialCapyPosition
                    )}
                }
            }
        }
        if (validInputs.contains(userInput)) {
            when(uiState.value.answerType) {
                AnswerType.COLUMN -> moveCapybaraInColumn(userInput)
                AnswerType.ROW -> TODO()
                AnswerType.BOX -> TODO()
                AnswerType.COL_ALIGN -> TODO()
                AnswerType.ROW_ALIGN -> TODO()
                AnswerType.BOX_ALIGN -> TODO()
            }
        }
    }


    private fun moveCapybaraInColumn(userInput: String) {
        when (userInput) {
            "horizontalAlignment = Alignment.End" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(horizontalAlignment = Alignment.End)
                    )
                )}
            }
            "horizontalAlignment = Alignment.Start" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(horizontalAlignment = Alignment.Start)
                    )
                )}
            }
            "horizontalAlignment = Alignment.CenterHorizontally" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(horizontalAlignment = Alignment.CenterHorizontally)
                    )
                )}
            }
            "verticalArrangement = Arrangement.Top" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(verticalArrangement = Arrangement.Top)
                    )
                )}
            }
            "verticalArrangement = Arrangement.Center" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(verticalArrangement = Arrangement.Center)
                    )
                )}
            }
            "verticalArrangement = Arrangement.Bottom" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(verticalArrangement = Arrangement.Bottom)
                    )
                )}
            }
            "verticalArrangement = Arrangement.SpaceAround" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(verticalArrangement = Arrangement.SpaceAround)
                    )
                )}
            }
            "verticalArrangement = Arrangement.SpaceBetween" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(verticalArrangement = Arrangement.SpaceBetween)
                    )
                )}
            }
            "verticalArrangement = Arrangement.SpaceEvenly" -> {
                _uiState.update { it.copy(
                    capybaraStageLayout = it.capybaraStageLayout?.copy(
                        container = UiContainer.Column(verticalArrangement = Arrangement.SpaceEvenly)
                    )
                )}
            }
        }
    }

    private fun loadLevel() {
        val levelConfig = levelRepository.getLevelConfig(levelNumber)

        correctAnswer = levelConfig.correctAnswer
        validInputs = levelConfig.validInput
        initialCapyPosition = levelConfig.initialUserStageLayout

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

                fruitStageLayout = levelConfig.stageLayout,
                capybaraStageLayout = levelConfig.initialUserStageLayout
            )
        }
    }

}
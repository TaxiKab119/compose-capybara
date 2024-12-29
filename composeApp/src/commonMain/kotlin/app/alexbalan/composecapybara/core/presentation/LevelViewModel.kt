package app.alexbalan.composecapybara.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.alexbalan.composecapybara.core.data.AnswerType
import app.alexbalan.composecapybara.core.data.LevelRepository
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer
import app.alexbalan.composecapybara.core.data.ui_datastore.UiAnswerMappings
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
                AnswerType.COLUMN -> {
                    moveCapybaraWholeContainer(userInput, UiAnswerMappings.wholeColumnAnswerMappings)
                }
                AnswerType.ROW -> {
                    moveCapybaraWholeContainer(userInput, UiAnswerMappings.wholeRowAnswerMappings)
                }
                AnswerType.BOX -> {
                    moveCapybaraWholeContainer(userInput, UiAnswerMappings.wholeBoxAnswerMappings)
                }
                AnswerType.COL_ALIGN -> TODO()
                AnswerType.ROW_ALIGN -> TODO()
                AnswerType.BOX_ALIGN -> TODO()
            }
        }
    }


    private fun moveCapybaraWholeContainer(userInput: String, answerMappings: Map<String, UiContainer>) {
        answerMappings[userInput]?.let { newContainer ->
            _uiState.update { state ->
                state.copy(
                    capybaraStageLayout = state.capybaraStageLayout?.copy(
                        container = newContainer
                    )
                )
            }
        }
    }

    private fun moveIndividualCapybara(userInput: String, answerMappings: Map<String, UiContainer>) {
        // TODO - Fine tune movements using Modifier.align()
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
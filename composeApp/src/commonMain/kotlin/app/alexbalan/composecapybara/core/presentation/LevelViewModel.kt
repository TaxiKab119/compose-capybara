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
    private var initialCapyPosition: StageLayout? = null
    private var validInputs: Set<String> = emptySet()
    private var validInputs2: Set<String> = emptySet()
    private var userInput1AnswerType: AnswerType = AnswerType.COLUMN
    private var userInput2AnswerType: AnswerType = AnswerType.COLUMN

    private val _uiState = MutableStateFlow(LevelScreenUiState(levelNumber))
    val uiState = _uiState.asStateFlow()
        .onStart { loadLevel() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            LevelScreenUiState(levelNumber)
        )


    fun updateUserInput(userInput: String, textFieldNumber: Int) {
        when(textFieldNumber) {
            1 -> {
                _uiState.update { screenState ->
                    screenState.copy(
                        codeFieldState1 = screenState.codeFieldState1.copy(userInput = userInput)
                    )
                }
                // Cancel and start new job only for field 1
                isInputCorrectJob1?.cancel()
                isInputCorrectJob1 = viewModelScope.launch {
                    delay(500)
                    isUserInputCorrect(userInput, 1)
                }
            }
            2 -> {
                _uiState.update { screenState ->
                    screenState.copy(
                        codeFieldState2 = screenState.codeFieldState2?.copy(userInput = userInput)
                    )
                }
                // Cancel and start new job only for field 2
                isInputCorrectJob2?.cancel()
                isInputCorrectJob2 = viewModelScope.launch {
                    delay(500)
                    isUserInputCorrect(userInput, 2)
                }
            }
        }
    }

    // Track jobs separately for each field
    private var isInputCorrectJob1: Job? = null
    private var isInputCorrectJob2: Job? = null
    private fun isUserInputCorrect(userInput: String, textFieldNumber: Int) {
        _uiState.update {
            when (textFieldNumber) {
                1 -> {
                    it.copy(
                        codeFieldState1 = it.codeFieldState1.copy(isCorrect = false),
                        capybaraStageLayout = if (!validInputs.contains(userInput)) initialCapyPosition else it.capybaraStageLayout
                    )
                }
                2 -> {
                    it.copy(
                        codeFieldState2 = it.codeFieldState2?.copy(isCorrect = false),
                        capybaraStageLayout = if (!validInputs2.contains(userInput)) initialCapyPosition else it.capybaraStageLayout
                    )
                }
                else -> it
            }
        }
        when (textFieldNumber) {
            1 -> if (validInputs.contains(userInput)) moveCapyByContainer(userInput, userInput1AnswerType)
            2 -> if (validInputs2.contains(userInput)) moveCapyByContainer(userInput, userInput2AnswerType)
            else -> return
        }
        if (uiState.value.codeFieldState1.userInput in uiState.value.codeFieldState1.validInput) {
            moveCapyByContainer(uiState.value.codeFieldState1.userInput, userInput1AnswerType)
        } else if (uiState.value.codeFieldState2?.isCorrect == true) {
            uiState.value.codeFieldState2?.let {
                if (it.userInput in it.validInput) {
                    moveCapyByContainer(it.userInput, userInput2AnswerType)
                }
            }
        }
        updateShowLevelCompleted()
    }

    private fun updateShowLevelCompleted() {
        val isCorrect =
            uiState.value.correctContainer == uiState.value.capybaraStageLayout?.container
        _uiState.update { it.copy(showCorrect = isCorrect) }
    }

    private fun moveCapyByContainer(userInput: String, answerType: AnswerType) {
        when(answerType) {
            AnswerType.COLUMN -> {
                moveCapybaraWholeContainerLayout(userInput, UiAnswerMappings.wholeColumnAnswerMappings)
            }
            AnswerType.ROW -> {
                moveCapybaraWholeContainerLayout(userInput, UiAnswerMappings.wholeRowAnswerMappings)
            }
            AnswerType.BOX -> {
                moveCapybaraWholeContainerLayout(userInput, UiAnswerMappings.wholeBoxAnswerMappings)
            }
            AnswerType.COL_ALIGN -> TODO()
            AnswerType.ROW_ALIGN -> TODO()
            AnswerType.BOX_ALIGN -> TODO()
        }
    }

    private fun moveCapybaraWholeContainerLayout(userInput: String, answerMappings: Map<String, UiContainer>) {
        answerMappings[userInput]?.let { newContainer ->
            _uiState.update { state ->
                state.copy(
                    capybaraStageLayout = state.capybaraStageLayout?.let { currentLayout ->
                        currentLayout.copy(
                            container = when (currentLayout.container) {
                                is UiContainer.Row -> {
                                    (newContainer as UiContainer.Row).let { new ->
                                        (currentLayout.container).copy(
                                            horizontalArrangement = new.horizontalArrangement ?: currentLayout.container.horizontalArrangement,
                                            verticalAlignment = new.verticalAlignment ?: currentLayout.container.verticalAlignment
                                        )
                                    }
                                }
                                is UiContainer.Column -> {
                                    (newContainer as UiContainer.Column).let { new ->
                                        (currentLayout.container).copy(
                                            verticalArrangement = new.verticalArrangement ?: currentLayout.container.verticalArrangement,
                                            horizontalAlignment = new.horizontalAlignment ?: currentLayout.container.horizontalAlignment
                                        )
                                    }
                                }

                                is UiContainer.Box ->  {
                                    (newContainer as UiContainer.Box).let { new ->
                                        (currentLayout.container).copy(
                                            contentAlignment = new.contentAlignment ?: currentLayout.container.contentAlignment
                                        )
                                    }
                                }
                            }
                        )
                    }
                )
            }
        }
    }

    private fun moveIndividualCapybara(userInput: String, answerMappings: Map<String, UiContainer>) {
        // TODO - Fine tune movements using Modifier.align()
    }

    private fun loadLevel() {
        val levelConfig = levelRepository.getLevelConfig(levelNumber)

        validInputs = levelConfig.codeFieldState1.validInput
        validInputs2 = levelConfig.codeFieldState2?.validInput ?: emptySet()
        userInput1AnswerType = levelConfig.codeFieldState1.answerType
        userInput2AnswerType = levelConfig.codeFieldState2?.answerType ?: AnswerType.COLUMN

        initialCapyPosition = levelConfig.initialUserStageLayout

        _uiState.update {
            it.copy(
                preamble = levelConfig.preamble,
                instructions = levelConfig.instructions,
                hints = levelConfig.hints,
                correctContainer = levelConfig.stageLayout?.container ?: UiContainer.Column(), // TODO - update default value

                codeFieldState1 = levelConfig.codeFieldState1,
                codeFieldState2 = levelConfig.codeFieldState2,
                codeFieldState3 = levelConfig.codeFieldState3,
                codeFieldState4 = levelConfig.codeFieldState4,
                numUserInputLines = levelConfig.numUserInputLines,

                fruitStageLayout = levelConfig.stageLayout,
                capybaraStageLayout = levelConfig.initialUserStageLayout
            )
        }
    }
}
package app.alexbalan.composecapybara.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.alexbalan.composecapybara.core.data.AnswerType
import app.alexbalan.composecapybara.core.data.LevelRepository
import app.alexbalan.composecapybara.core.data.stage.ElementPosition
import app.alexbalan.composecapybara.core.data.stage.UiContainer
import app.alexbalan.composecapybara.core.data.ui_datastore.UiAnswerMappings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

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

    private fun loadLevel() {
        val levelConfig = levelRepository.getLevelConfig(levelNumber)
        _uiState.update {
            it.copy(
                correctContainer = levelConfig.stageLayout?.container ?: UiContainer.Column(),
                correctElementPositions = levelConfig.stageLayout?.elements ?: listOf(),

                preamble = levelConfig.preamble,
                instructions = levelConfig.instructions,
                hints = levelConfig.hints,

                numUserInputLines = levelConfig.numUserInputLines,
                codeFieldState1 = levelConfig.codeFieldState1,
                codeFieldState2 = levelConfig.codeFieldState2,
                codeFieldState3 = levelConfig.codeFieldState3,
                codeFieldState4 = levelConfig.codeFieldState4,

                cushionStageLayout = levelConfig.stageLayout,
                capybaraStageLayout = levelConfig.initialUserStageLayout,
                initialCapyPosition = levelConfig.initialUserStageLayout,
            )
        }
    }


    fun updateUserInput(userInput: String, textFieldNumber: Int) {
        when(textFieldNumber) {
            1 -> {
                _uiState.update { screenState ->
                    screenState.copy(
                        codeFieldState1 = screenState.codeFieldState1.copy(userInput = userInput)
                    )
                }
                isUserInputCorrect(userInput, 1)
            }
            2 -> {
                _uiState.update { screenState ->
                    screenState.copy(
                        codeFieldState2 = screenState.codeFieldState2?.copy(userInput = userInput)
                    )
                }
                isUserInputCorrect(userInput, 2)
            }
            3 -> {
                _uiState.update { screenState ->
                    screenState.copy(
                        codeFieldState3 = screenState.codeFieldState3?.copy(userInput = userInput)
                    )
                }
                isUserInputCorrect(userInput, 3)
            }
            4 -> {
                _uiState.update { screenState ->
                    screenState.copy(
                        codeFieldState4 = screenState.codeFieldState4?.copy(userInput = userInput)
                    )
                }
                isUserInputCorrect(userInput, 4)
            }
            else -> return
        }
    }

    private fun isUserInputCorrect(userInput: String, textFieldNumber: Int) {
        val cfs1 = uiState.value.codeFieldState1
        val cfs2 = uiState.value.codeFieldState2
        val cfs3 = uiState.value.codeFieldState3
        val cfs4 = uiState.value.codeFieldState4

        val validInputSet = when(textFieldNumber) {
            1 -> cfs1.validInputs
            2 -> cfs2?.validInputs ?: setOf()
            3 -> cfs3?.validInputs ?: setOf()
            4 -> cfs4?.validInputs ?: setOf()
            else -> setOf()
        }

        // Reset position if input is invalid
        if (!validInputSet.contains(userInput)) {
            _uiState.update { it.copy(capybaraStageLayout = uiState.value.initialCapyPosition) }
        }

        // This block adds alignment/arrangement, if they are valid, back to the container/elements
        // Otherwise, existing valid fields can be lost in moveCapybara()
        if (cfs1.userInput in cfs1.validInputs) {
            moveCapybara(cfs1.userInput, cfs1.answerType, 1)
        }
        if (cfs2?.validInputs?.contains(cfs2.userInput) == true) {
            moveCapybara(cfs2.userInput, cfs2.answerType, 2)
        }
        if (cfs3?.validInputs?.contains(cfs3.userInput) == true) {
            moveCapybara(cfs3.userInput, cfs3.answerType, 3)
        }
        if (cfs4?.validInputs?.contains(cfs4.userInput) == true) {
            moveCapybara(cfs4.userInput, cfs4.answerType, 4)
        }

        updateShowLevelCompleted()
    }

    private fun updateShowLevelCompleted() {
        val isCorrect =
            uiState.value.correctContainer == uiState.value.capybaraStageLayout?.container &&
            uiState.value.correctElementPositions == uiState.value.capybaraStageLayout?.elements
        _uiState.update { it.copy(showCorrect = isCorrect) }
    }

    private fun moveCapybara(userInput: String, answerType: AnswerType, codeFieldNumber: Int) {

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
            AnswerType.BOX_ALIGN, AnswerType.COL_ALIGN, AnswerType.ROW_ALIGN -> {
                val capyIndex = when(codeFieldNumber) {
                    1 -> uiState.value.codeFieldState1.elementIndexToModify
                    2 -> uiState.value.codeFieldState2?.elementIndexToModify ?: -1
                    3 -> uiState.value.codeFieldState3?.elementIndexToModify ?: -1
                    4 -> uiState.value.codeFieldState4?.elementIndexToModify ?: -1
                    else -> -1
                }
                moveIndividualCapybara(userInput, answerType, capyIndex)
            }
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

    private fun moveIndividualCapybara(userInput: String, answerType: AnswerType, capyIndex: Int) {
        when(answerType) {
            AnswerType.COL_ALIGN -> {
                UiAnswerMappings.columnAlignmentMappings[userInput]?.let { newAlignment ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            capybaraStageLayout = currentState.capybaraStageLayout?.let { currentLayout ->
                                currentLayout.copy(
                                    elements = currentLayout.elements.mapIndexed { index, capyPosition ->
                                        if (index == capyIndex && capyPosition is ElementPosition.InColumn) {
                                            capyPosition.copy(alignment = newAlignment)
                                        } else {
                                            capyPosition
                                        }
                                    }
                                )
                            }
                        )
                    }
                }
            }
            AnswerType.ROW_ALIGN -> {
                UiAnswerMappings.rowAlignmentMappings[userInput]?.let { newAlignment ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            capybaraStageLayout = currentState.capybaraStageLayout?.let { currentLayout ->
                                currentLayout.copy(
                                    elements = currentLayout.elements.mapIndexed { index, capyPosition ->
                                        if (index == capyIndex && capyPosition is ElementPosition.InRow) {
                                            capyPosition.copy(alignment = newAlignment)
                                        } else {
                                            capyPosition
                                        }
                                    }
                                )
                            }
                        )
                    }
                }
            }
            AnswerType.BOX_ALIGN -> {
                UiAnswerMappings.boxAlignmentMappings[userInput]?.let { newAlignment ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            capybaraStageLayout = currentState.capybaraStageLayout?.let { currentLayout ->
                                currentLayout.copy(
                                    elements = currentLayout.elements.mapIndexed { index, capyPosition ->
                                        if (index == capyIndex && capyPosition is ElementPosition.InBox) {
                                            capyPosition.copy(alignment = newAlignment)
                                        } else {
                                            capyPosition
                                        }
                                    }
                                )
                            }
                        )
                    }
                }
            }
            else -> return
        }
    }
}
package app.alexbalan.composecapybara.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.alexbalan.composecapybara.core.data.settings.GameDifficulty
import app.alexbalan.composecapybara.core.data.ui_datastore.LayoutConcept
import app.alexbalan.composecapybara.core.presentation.components.Capybara
import app.alexbalan.composecapybara.core.presentation.components.CodeBlock
import app.alexbalan.composecapybara.core.presentation.components.ConceptLinkDialog
import app.alexbalan.composecapybara.core.presentation.components.Cushion
import app.alexbalan.composecapybara.core.presentation.components.GameCompleteDialog
import app.alexbalan.composecapybara.core.presentation.components.GameNotCompleteDialog
import app.alexbalan.composecapybara.core.presentation.components.LevelInstructions
import app.alexbalan.composecapybara.core.presentation.components.LevelSelector
import app.alexbalan.composecapybara.core.presentation.components.LevelStageRoot
import app.alexbalan.composecapybara.core.presentation.components.LevelsDropDown
import app.alexbalan.composecapybara.core.presentation.components.LinkableText
import app.alexbalan.composecapybara.core.presentation.components.SettingsDialog
import app.alexbalan.composecapybara.core.presentation.components.StageElement
import app.alexbalan.composecapybara.core.presentation.theme.AppColors
import composecapybara.composeapp.generated.resources.Res
import composecapybara.composeapp.generated.resources.compose_capybara_title
import org.jetbrains.compose.resources.vectorResource


@Composable
fun GameScreenRoot(
    viewModel: LevelViewModel,
    onForwardClick: (Int) -> Unit,
    onBackwardClick: (Int) -> Unit,
    onLevelSelected: (Int) -> Unit,
    onTextUpdated1: (String) -> Unit,
    onTextUpdated2: (String) -> Unit,
    onTextUpdated3: (String) -> Unit,
    onTextUpdated4: (String) -> Unit,
    onColorBlindToggled: (Boolean) -> Unit,
    onDifficultyChanged: (GameDifficulty) -> Unit,
    onResetProgress: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.showFinalDialog) {
        if (uiState.isGameCompleted) {
            GameCompleteDialog(
                totalNumberLevels = uiState.totalNumberLevels,
                onDismiss = { viewModel.closeFinalDialog() }
            )
        } else {
            GameNotCompleteDialog(
                onDismiss = { viewModel.closeFinalDialog() },
                totalNumberLevels = uiState.totalNumberLevels,
                numberCompleted = uiState.completedLevels.size
            )
        }
    }
    GameScreen(
        onForwardClick = { onForwardClick(it) },
        onBackwardClick = { onBackwardClick(it) },
        onTextUpdated1 = { onTextUpdated1(it) },
        onTextUpdated2 = { onTextUpdated2(it) },
        onTextUpdated3 = { onTextUpdated3(it) },
        onTextUpdated4 = { onTextUpdated4(it) },
        onLevelSelected = onLevelSelected,
        onColorBlindToggled = onColorBlindToggled,
        onDifficultyChanged = onDifficultyChanged,
        onResetProgress = onResetProgress,
        uiState = uiState,
    )
}

@Composable
fun GameScreen(
    uiState: LevelScreenUiState,
    onForwardClick: (Int) -> Unit,
    onBackwardClick: (Int) -> Unit,
    onTextUpdated1: (String) -> Unit,
    onTextUpdated2: (String) -> Unit,
    onTextUpdated3: (String) -> Unit,
    onTextUpdated4: (String) -> Unit,
    onColorBlindToggled: (Boolean) -> Unit,
    onDifficultyChanged: (GameDifficulty) -> Unit,
    onResetProgress: () -> Unit,
    onLevelSelected: (Int) -> Unit,
) {
    var selectedConcept by remember { mutableStateOf<LayoutConcept?>(null) }
    var showLevelsDropdown by remember { mutableStateOf(false) }
    var showSettings by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    showLevelsDropdown = false
                }
            }
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(AppColors.lightBlue)
                .padding(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                selectedConcept?.let { concept ->
                    ConceptLinkDialog(concept = concept) {
                        selectedConcept = null
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        imageVector = vectorResource(Res.drawable.compose_capybara_title),
                        contentDescription = "Compose Capybara",
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        LevelSelector(
                            onForwardClick = { onForwardClick(it) },
                            onBackwardClick = { onBackwardClick(it) },
                            levelNumber = uiState.levelNumber,
                            totalLevels = uiState.totalNumberLevels,
                            onShowLevelsClicked = {
                                showLevelsDropdown = !showLevelsDropdown
                            },
                            modifier = Modifier.align(Alignment.TopEnd)
                        )
                    }
                }
                if (uiState.settingsState.difficulty == GameDifficulty.BEGINNER) {
                    LinkableText(uiState.preamble) {
                        selectedConcept = it
                    }
                    Spacer(Modifier.height(12.dp))
                    if (uiState.instructions.isNotEmpty()) {
                        LevelInstructions(uiState.instructions)
                        Spacer(Modifier.height(12.dp))
                    }
                    if (uiState.hints.isNotEmpty()) {
                        uiState.hints.forEach {
                            LinkableText(it) { concept ->
                                selectedConcept = concept
                            }
                            Spacer(Modifier.height(12.dp))
                        }
                    }
                }
                CodeBlock(
                    codeFieldState1 = uiState.codeFieldState1,
                    codeFieldState2 = uiState.codeFieldState2,
                    codeFieldState3 = uiState.codeFieldState3,
                    codeFieldState4 = uiState.codeFieldState4,
                    onTextUpdated1 = onTextUpdated1,
                    onTextUpdated2 = onTextUpdated2,
                    onTextUpdated3 = onTextUpdated3,
                    onTextUpdated4 = onTextUpdated4,
                    isCorrect = uiState.showCorrect,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onNextClicked = { onForwardClick(uiState.levelNumber) }
                )
            }
            if (showLevelsDropdown) {
                LevelsDropDown(
                    totalNumberLevels = uiState.totalNumberLevels,
                    completedLevels = uiState.completedLevels,
                    currentLevel = uiState.levelNumber,
                    modifier = Modifier.align(Alignment.TopEnd),
                    onLevelSelected = {
                        if (it != uiState.levelNumber) {
                            onLevelSelected(it)
                        }
                        showLevelsDropdown = false
                    },
                    onSettingsSelected = {
                        showSettings = true
                        showLevelsDropdown = false
                    }
                )
            }
            if (showSettings) {
                SettingsDialog(
                    settingsState = uiState.settingsState,
                    onDismiss = { showSettings = false },
                    onColorBlindToggled = onColorBlindToggled,
                    onDifficultyChanged = onDifficultyChanged,
                    onResetProgress = onResetProgress
                )
            }
        }
        Box(
            Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            // Static Stage elements
            LevelStageRoot(
                levelNumber = uiState.levelNumber,
                stageLayout = uiState.cushionStageLayout,
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppColors.stageGreen)
                    .padding(12.dp),
                isCorrect = uiState.showCorrect,
                stageElement = StageElement.Cushion { cushionType, modifier ->
                    Cushion(
                        cushionType = cushionType,
                        modifier = modifier,
                        isColorblind = uiState.settingsState.isColorBlindMode
                    )
                }
            )

            // Dynamic gameplay layer (user-controlled)
            LevelStageRoot(
                levelNumber = uiState.levelNumber,
                stageLayout = uiState.capybaraStageLayout,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                isCorrect = uiState.showCorrect,
                stageElement = StageElement.Capybara { cushionType, modifier, isCorrect ->
                    Capybara(
                        cushionType = cushionType,
                        isSleepy = isCorrect,
                        modifier = modifier,
                        isColorblind = uiState.settingsState.isColorBlindMode
                    )
                }
            )
        }
    }
}
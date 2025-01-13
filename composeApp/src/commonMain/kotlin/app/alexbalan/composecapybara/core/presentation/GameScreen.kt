package app.alexbalan.composecapybara.core.presentation

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.alexbalan.composecapybara.core.data.stage.CushionType
import app.alexbalan.composecapybara.core.data.ui_datastore.LayoutConcept
import app.alexbalan.composecapybara.core.presentation.components.CodeBlock
import app.alexbalan.composecapybara.core.presentation.components.LevelStageRoot
import app.alexbalan.composecapybara.core.presentation.components.StageElement
import app.alexbalan.composecapybara.core.presentation.theme.AppColors
import app.alexbalan.composecapybara.core.presentation.theme.TextStyles
import app.alexbalan.composecapybara.core.presentation.util.applyIfElse
import composecapybara.composeapp.generated.resources.Res
import composecapybara.composeapp.generated.resources.bara_blue_sleepy
import composecapybara.composeapp.generated.resources.bara_orange_sleepy
import composecapybara.composeapp.generated.resources.bara_purple_sleepy
import composecapybara.composeapp.generated.resources.blue_capybara
import composecapybara.composeapp.generated.resources.blue_cushion
import composecapybara.composeapp.generated.resources.compose_capybara_title
import composecapybara.composeapp.generated.resources.orange_capybara
import composecapybara.composeapp.generated.resources.orange_cushion
import composecapybara.composeapp.generated.resources.purple_capybara
import composecapybara.composeapp.generated.resources.purple_cushion
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
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GameScreen(
        onForwardClick = { onForwardClick(it) },
        onBackwardClick = { onBackwardClick(it) },
        onTextUpdated1 = { onTextUpdated1(it) },
        onTextUpdated2 = { onTextUpdated2(it) },
        onTextUpdated3 = { onTextUpdated3(it) },
        onTextUpdated4 = { onTextUpdated4(it) },
        onLevelSelected = onLevelSelected,
        uiState = uiState
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
    onLevelSelected: (Int) -> Unit,
) {
    var selectedConcept by remember { mutableStateOf<LayoutConcept?>(null) }
    var showLevelsDropdown by remember { mutableStateOf(false) }

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
                        modifier = Modifier.sizeIn(
                            minWidth = 100.dp,
                            minHeight = 50.dp,
                            maxHeight = 200.dp,
                            maxWidth = 400.dp
                        )
                    )
                    LevelSelector(
                        onForwardClick = { onForwardClick(it) },
                        onBackwardClick = { onBackwardClick(it) },
                        levelNumber = uiState.levelNumber,
                        totalLevels = uiState.totalNumberLevels,
                        onShowLevelsClicked = {
                            showLevelsDropdown = !showLevelsDropdown
                        },
                        modifier = Modifier.defaultMinSize(minWidth = 100.dp),
                    )
                }
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
                    }
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
                    Cushion(cushionType, modifier)
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
                    Capybara(cushionType, isCorrect, modifier)
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LevelsDropDown(
    totalNumberLevels: Int,
    completedLevels: Set<Int>,
    currentLevel: Int,
    modifier: Modifier = Modifier,
    onLevelSelected: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .offset(x = (-12).dp, y = 40.dp),
        elevation = 4.dp,
        backgroundColor = AppColors.stageGreen
    ) {
        FlowRow(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 4
        ) {
            for (level in 1..totalNumberLevels) {
                LevelDot(
                    level = level,
                    isCompleted = level in completedLevels,
                    isCurrentLevel = level == currentLevel,
                    onClick = { onLevelSelected(it) }
                )
            }
        }
    }

}

@Composable
private fun LevelDot(
    level: Int,
    isCompleted: Boolean,
    isCurrentLevel: Boolean,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(
                color = if (isCompleted) AppColors.lightBlue else AppColors.CodeBlock.Background,
                shape = CircleShape
            )
            .applyIfElse(
                isCurrentLevel,
                Modifier.border(
                    width = 3.dp,
                    color = Color.White,
                    shape = CircleShape
                )
            )
            .clickable(onClick = { onClick(level) }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = level.toString(),
            color = AppColors.levelText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.offset(y = (-1).dp) // Slight offset to account for text baseline
        )
    }
}

@Composable
fun LevelSelector(
    levelNumber: Int,
    totalLevels: Int,
    onBackwardClick: (Int) -> Unit,
    onForwardClick: (Int) -> Unit,
    onShowLevelsClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 2.dp,
                        bottomStart = 2.dp
                    )
                )
                .background(
                    color = Color.White.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(
                        topStart = 2.dp,
                        bottomStart = 2.dp
                    )
                )
                .clickable(enabled = levelNumber > 1) { onBackwardClick(levelNumber) }
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back",
                tint = if (levelNumber > 1) AppColors.levelText else AppColors.levelText.copy(alpha = 0.4f)
            )
        }
        Spacer(Modifier.width(2.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onShowLevelsClicked() }
                .background(
                    color = Color.White.copy(alpha = 0.4f)
                )
                .padding(8.dp)
        ) {
            Text("Level $levelNumber of $totalLevels")
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Show levels"
            )
        }
        Spacer(Modifier.width(2.dp))
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = 2.dp,
                        bottomEnd = 2.dp
                    )
                )
                .background(
                    color = Color.White.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(
                        topEnd = 2.dp,
                        bottomEnd = 2.dp
                    )
                )
                .clickable(enabled = levelNumber in 1..19) { onForwardClick(levelNumber) }
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "Back",
                tint = if (levelNumber in 1..19) AppColors.levelText else AppColors.levelText.copy(alpha = 0.4f)
            )
        }
    }
}

@Composable
fun LevelInstructions(
    instructions: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        instructions.forEach { instruction ->
            LinkableText(instruction, appendBullet = true)
        }
    }
}

@Composable
fun LinkableText(
    text: String,
    appendBullet: Boolean = false,
    attachLinks: Boolean = true,
    onConceptClick: (LayoutConcept) -> Unit = {}
) {
    val annotatedString = buildAnnotatedString {
        var remainingText = text
        val marker = "**"

        while (remainingText.contains(marker)) {
            val beforeMarker = remainingText.substringBefore(marker)
            append(beforeMarker)
            remainingText = remainingText.substringAfter(marker)

            val highlightedText = remainingText.substringBefore(marker)
            val concept = LayoutConcept.fromKeyword(highlightedText)

            if (concept != null && attachLinks) {
                withLink(
                    LinkAnnotation.Clickable(
                        tag = concept.tag,
                        styles = TextStyles.conceptLinkStyle(),
                        linkInteractionListener = { _ ->
                            onConceptClick(concept)
                        }
                    )
                ) {
                    append(highlightedText)
                }
            } else {
                if (appendBullet) append("  •  ")
                withStyle(
                    TextStyles.nonClickableHighlightStyle()
                ) {
                    append(highlightedText)
                }

            }
            remainingText = remainingText.substringAfter(marker)
        }
        // Add any remaining text after the last marker
        append(remainingText)
    }
    Text(
        text = annotatedString,
        fontSize = 18.sp,
        color = AppColors.levelText
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ConceptLinkDialog(
    concept: LayoutConcept,
    modifier: Modifier = Modifier,
    onCloseDialog: () -> Unit,
) {
    AlertDialog(
        modifier = modifier.padding(12.dp),
        onDismissRequest = { onCloseDialog() },
        backgroundColor = AppColors.lightBlue,
        title = {
            Text(
                text = concept.keyword,
                style = TextStyles.conceptLinkTextStyle().copy(
                    fontSize = 24.sp
                )
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LinkableText(concept.description, attachLinks = false)
                if (concept.values.isNotEmpty()) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        concept.values.forEach { value ->
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = AppColors.CodeBlock.Background,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            ) {
                                Text(
                                    value,
                                    Modifier.padding(4.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onCloseDialog,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = AppColors.levelText
                )
            ) {
                Text("Ok")
            }
        }
    )
}

@Composable
fun Capybara(
    cushionType: CushionType,
    isSleepy: Boolean = false,
    modifier: Modifier = Modifier,
) {
    // breathing animation
    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .size(128.dp)
            .padding(12.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        contentAlignment = Alignment.Center
    ) {
        val capyRes = when (cushionType) {
            CushionType.BLUE -> if (isSleepy) Res.drawable.bara_blue_sleepy else Res.drawable.blue_capybara
            CushionType.ORANGE -> if (isSleepy) Res.drawable.bara_orange_sleepy else Res.drawable.orange_capybara
            CushionType.PURPLE -> if (isSleepy) Res.drawable.bara_purple_sleepy else Res.drawable.purple_capybara
        }
        Image(
            imageVector = vectorResource(capyRes),
            contentDescription = "Capybara",
        )
    }
}

@Composable
fun Cushion(
    cushionType: CushionType,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(128.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {

        val resId = when (cushionType) {
            CushionType.BLUE -> Res.drawable.blue_cushion
            CushionType.ORANGE -> Res.drawable.orange_cushion
            CushionType.PURPLE -> Res.drawable.purple_cushion
        }
        Image(
            imageVector = vectorResource(resId),
            contentDescription = cushionType.name
        )
    }
}
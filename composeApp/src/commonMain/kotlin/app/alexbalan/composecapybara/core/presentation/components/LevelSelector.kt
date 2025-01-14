package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.alexbalan.composecapybara.core.presentation.theme.AppColors
import app.alexbalan.composecapybara.core.presentation.util.applyIfElse


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


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LevelsDropDown(
    totalNumberLevels: Int,
    completedLevels: Set<Int>,
    currentLevel: Int,
    onSettingsSelected: () -> Unit,
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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
            IconButton(
                onClick = onSettingsSelected
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = AppColors.levelText
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
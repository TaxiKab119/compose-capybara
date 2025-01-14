package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.alexbalan.composecapybara.core.data.settings.GameDifficulty
import app.alexbalan.composecapybara.core.presentation.SettingsState
import app.alexbalan.composecapybara.core.presentation.theme.AppColors


@Composable
fun SettingsDialog(
    settingsState: SettingsState,
    onDismiss: () -> Unit,
    onColorBlindToggled: (Boolean) -> Unit,
    onDifficultyChanged: (GameDifficulty) -> Unit,
    onResetProgress: () -> Unit
) {
    var showResetConfirmation by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            backgroundColor = AppColors.lightBlue
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.h5,
                    color = AppColors.levelText
                )

                // Colorblind Mode Switch
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Colorblind Mode", color = AppColors.levelText, fontWeight = FontWeight.Bold)
                        Switch(
                            checked = settingsState.isColorBlindMode,
                            onCheckedChange = onColorBlindToggled
                        )
                    }
                    if (settingsState.isColorBlindMode) {
                        LinkableText("The **orange** capybara likes **stars**.")
                        LinkableText("The **blue** capybara likes **hearts**.")
                    }
                }

                // Difficulty Selection
                Column {
                    Text("Difficulty", color = AppColors.levelText, fontWeight = FontWeight.Bold)
                    GameDifficulty.entries.forEach { difficulty ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = settingsState.difficulty == difficulty,
                                onClick = { onDifficultyChanged(difficulty) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                difficulty.name
                                    .lowercase()
                                    .replaceFirstChar { it.titlecase() } +
                                        ": ${difficulty.description}"
                            )
                        }
                    }
                }

                // Reset Progress Button
                Button(
                    onClick = { showResetConfirmation = true },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.error
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    Text("Reset Progress")
                }

                // Dialog Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Close", color = AppColors.levelText)
                    }
                }
            }
        }
    }

    // Reset Confirmation Dialog
    if (showResetConfirmation) {
        AlertDialog(
            onDismissRequest = { showResetConfirmation = false },
            title = { Text("Reset Progress") },
            text = { Text("Are you sure you want to reset all progress? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onResetProgress()
                        showResetConfirmation = false
                    }
                ) {
                    Text("Reset", color = AppColors.levelText)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirmation = false }) {
                    Text("Cancel", color = AppColors.levelText)
                }
            }
        )
    }
}
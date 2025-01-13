package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.alexbalan.composecapybara.core.data.ui_datastore.LayoutConcept
import app.alexbalan.composecapybara.core.presentation.theme.AppColors
import app.alexbalan.composecapybara.core.presentation.theme.TextStyles


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
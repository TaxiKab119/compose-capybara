package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.alexbalan.composecapybara.core.data.ui_datastore.LayoutConcept
import app.alexbalan.composecapybara.core.presentation.theme.AppColors
import app.alexbalan.composecapybara.core.presentation.theme.TextStyles

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
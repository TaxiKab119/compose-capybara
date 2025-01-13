package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.alexbalan.composecapybara.core.presentation.theme.AppColors
import app.alexbalan.composecapybara.core.presentation.theme.TextStyles
import composecapybara.composeapp.generated.resources.Res
import composecapybara.composeapp.generated.resources.party_capys_group_yay
import org.jetbrains.compose.resources.vectorResource

@Composable
fun GameCompleteDialog(
    onDismiss: () -> Unit,
    totalNumberLevels: Int,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    AlertDialog(
        modifier = modifier.padding(16.dp),
        onDismissRequest = onDismiss,
        backgroundColor = AppColors.lightBlue,
        title = {
            Text(
                text = "Congratulations!",
                style = TextStyles.conceptLinkTextStyle().copy(
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "You've competed all $totalNumberLevels levels! The capybaras, now well-rested, are throwing a party for you. You are amazing!",
                    fontSize = 16.sp,
                    color = AppColors.levelText
                )
                Text(
                    text = "You now know the basics of Jetpack Compose UI layouts; what a powerful thing that is! In fact, Compose Capybara is built entirely using the same concepts you just practiced.",
                    fontSize = 16.sp,
                    color = AppColors.levelText
                )
                Text(
                    text = "Compose Capybara uses Compose Multiplatform, which allows you to share the same code across Web, Desktop, iOS, and Android applications. Think of all the things you could build. Now go out there and do it!",
                    fontSize = 16.sp,
                    color = AppColors.levelText
                )
                Image(
                    imageVector = vectorResource(Res.drawable.party_capys_group_yay),
                    contentDescription = "Celebrating Capybaras"
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Connect with me (the guy behind the capybaras):",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.levelText
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SocialLink(
                        text = "GitHub",
                        onClick = { uriHandler.openUri("https://github.com/TaxiKab119") },
                        modifier = Modifier.weight(1f)
                    )
                    SocialLink(
                        text = "LinkedIn",
                        onClick = { uriHandler.openUri("https://linkedin.com/in/k-alexbalan") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = AppColors.levelText
                )
            ) {
                Text("Close")
            }
        }
    )
}

@Composable
fun GameNotCompleteDialog(
    onDismiss: () -> Unit,
    totalNumberLevels: Int,
    numberCompleted: Int,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier.padding(16.dp),
        onDismissRequest = onDismiss,
        backgroundColor = AppColors.lightBlue,
        title = {
            Text(
                text = "Great work! But...",
                style = TextStyles.conceptLinkTextStyle().copy(
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "You've competed $numberCompleted levels so far! The capybaras are still very sleepy and they're telling me you still need to complete ${totalNumberLevels - numberCompleted} levels. ",
                    fontSize = 16.sp,
                    color = AppColors.levelText
                )
                Text(
                    text = "However, they said if you finish every level, they might even throw you a party. Your progress will be saved even if you close your browser/app. So when you're ready, get back to it!",
                    fontSize = 16.sp,
                    color = AppColors.levelText
                )
                Text(
                    text = "I believe in you!",
                    fontSize = 16.sp,
                    color = AppColors.levelText
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = AppColors.levelText
                )
            ) {
                Text("Close")
            }
        }
    )
}

@Composable
private fun SocialLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = AppColors.levelText
        ),
        modifier = modifier
    ) {
        Text(text)
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Open $text",
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}
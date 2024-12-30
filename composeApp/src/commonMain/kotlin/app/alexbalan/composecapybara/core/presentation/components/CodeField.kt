package app.alexbalan.composecapybara.core.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeField(
    modifier: Modifier = Modifier,
    userInput: String,
    existingLinesBefore: List<String>,
    existingLinesAfter: List<String>,
    isCorrect: Boolean,
    numUserInputLines: Int,
    onTextUpdated: (String) -> Unit,
    onNextClicked: () -> Unit,
) {
    val numCodeLines =
        numUserInputLines + existingLinesBefore.size + existingLinesAfter.size

    Row(
        modifier = Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        CodeFieldLineNumbers(numCodeLines)
        Box(
            modifier = modifier
                .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                existingLinesBefore.forEach { MonospacedText(it) }
                CodeFieldTextInputField(
                    userInput = userInput,
                    numUserInputLines = numUserInputLines,
                    onTextUpdated = onTextUpdated,
                    textFieldOffset = "    "
                )
                existingLinesAfter.forEach { MonospacedText(it) }
            }
            CodeFieldButton(
                onClick = onNextClicked,
                isCorrect = isCorrect,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun CodeFieldButton(
    onClick: () -> Unit,
    isCorrect: Boolean,
    modifier: Modifier = Modifier
) {
    // infinite jiggle animation
    val rotation by rememberInfiniteTransition().animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(150, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // infinite scale animation
    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(300, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Button(
        onClick = onClick,
        enabled = isCorrect,
        modifier = modifier
            .graphicsLayer {
                if (isCorrect) {
                    rotationZ = rotation
                    scaleX = scale
                    scaleY = scale
                }
            }
            .padding(end = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Green
        )
    ) {
        Text("Next")
    }
}

@Composable
fun CodeFieldTextInputField(
    userInput: String,
    numUserInputLines: Int,
    onTextUpdated: (String) -> Unit,
    textFieldOffset: String = "",
    modifier: Modifier = Modifier
) {
    Row {
        MonospacedText(textFieldOffset)
        Box(
            modifier = modifier.padding(end = 8.dp)
        ) {
            BasicTextField(
                value = userInput,
                singleLine = numUserInputLines == 1,
                maxLines = numUserInputLines,
                onValueChange = { onTextUpdated(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                textStyle = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = FontFamily.Monospace
                )
            )
        }
    }
}


@Composable
fun CodeFieldLineNumbers(
    numLines: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
            )
    ) {
        repeat(numLines) {
            Text(
                text = "${it + 1}",
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun MonospacedText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontFamily = FontFamily.Monospace,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.body1.copy(
            fontSize = 16.sp,
            color = Color.DarkGray
        ),
        modifier = modifier
    )
}
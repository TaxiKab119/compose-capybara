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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.alexbalan.composecapybara.core.presentation.CodeFieldState
import app.alexbalan.composecapybara.core.presentation.theme.AppColors

@Composable
fun CodeBlock(
    codeFieldState1: CodeFieldState,
    isCorrect: Boolean,
    modifier: Modifier = Modifier,
    codeFieldState2: CodeFieldState? = null,
    codeFieldState3: CodeFieldState? = null,
    codeFieldState4: CodeFieldState? = null,
    onTextUpdated1: (String) -> Unit = {},
    onTextUpdated2: (String) -> Unit = {},
    onTextUpdated3: (String) -> Unit = {},
    onTextUpdated4: (String) -> Unit = {},
    onNextClicked: () -> Unit = {},
) {
    val numCodeLines by remember(
        codeFieldState1, codeFieldState2, codeFieldState3, codeFieldState4
    ) {
        derivedStateOf {
            codeFieldState1.totalLines +
            (codeFieldState2?.totalLines ?: 0) +
            (codeFieldState3?.totalLines ?: 0) +
            (codeFieldState4?.totalLines ?: 0) + 3 // Buffer of 3 lines for "Next" button
        }
    }

    Row(
        modifier = Modifier
            .background(AppColors.CodeBlock.Background, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        CodeFieldLineNumbers(numCodeLines)
        Box(
            modifier = modifier
                .background(AppColors.CodeBlock.Background, shape = RoundedCornerShape(4.dp))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                codeFieldState1.existingLinesBefore.forEach { MonospacedText(it) }
                CodeFieldTextInputField(
                    userInput = codeFieldState1.userInput,
                    numUserInputLines = codeFieldState1.numUserInputLines,
                    onTextUpdated = onTextUpdated1,
                    prependedText = codeFieldState1.prependedText,
                    appendedText = codeFieldState1.appendedText
                )
                codeFieldState1.existingLinesAfter.forEach { MonospacedText(it) }
                codeFieldState2?.let { codeFieldState ->
                    codeFieldState.existingLinesBefore.forEach { MonospacedText(it) }
                    CodeFieldTextInputField(
                        userInput = codeFieldState.userInput,
                        numUserInputLines = codeFieldState.numUserInputLines,
                        onTextUpdated = onTextUpdated2,
                        prependedText = codeFieldState.prependedText,
                        appendedText = codeFieldState.appendedText
                    )
                    codeFieldState.existingLinesAfter.forEach { MonospacedText(it) }
                }
                codeFieldState3?.let { codeFieldState ->
                    codeFieldState.existingLinesBefore.forEach { MonospacedText(it) }
                    CodeFieldTextInputField(
                        userInput = codeFieldState.userInput,
                        numUserInputLines = codeFieldState.numUserInputLines,
                        onTextUpdated = onTextUpdated3,
                        prependedText = codeFieldState.prependedText,
                        appendedText = codeFieldState.appendedText
                    )
                    codeFieldState.existingLinesAfter.forEach { MonospacedText(it) }
                }
                codeFieldState4?.let { codeFieldState ->
                    codeFieldState.existingLinesBefore.forEach { MonospacedText(it) }
                    CodeFieldTextInputField(
                        userInput = codeFieldState.userInput,
                        numUserInputLines = codeFieldState.numUserInputLines,
                        onTextUpdated = onTextUpdated4,
                        prependedText = codeFieldState.prependedText,
                        appendedText = codeFieldState.appendedText
                    )
                    codeFieldState.existingLinesAfter.forEach { MonospacedText(it) }
                }
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
            .padding(end = 14.dp),
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
    modifier: Modifier = Modifier,
    appendedText: String = "",
    prependedText: String = "",
    onTextUpdated: (String) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(end = 14.dp)
    ) {
        MonospacedText(prependedText)
        Box(
            Modifier.weight(1f)
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
        if (appendedText.isNotEmpty()) {
            MonospacedText(appendedText)
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
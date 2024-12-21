package app.alexbalan.composecapybara.core.presentation.components

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .background(
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
                )
        ) {
            repeat(numCodeLines) {
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
                existingLinesBefore.forEach {
                    Text(
                        text = it,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    )
                }
                Row {
                    Text(
                        text = "\t\t",
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        ),
                    )
                    Box(
                        modifier = Modifier.padding(end = 8.dp)
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
                existingLinesAfter.forEach {
                    Text(
                        text = it,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    )
                }
            }
            Button(
                onClick = onNextClicked,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp)
            ) {
                Text("Next")
            }
        }
    }
}
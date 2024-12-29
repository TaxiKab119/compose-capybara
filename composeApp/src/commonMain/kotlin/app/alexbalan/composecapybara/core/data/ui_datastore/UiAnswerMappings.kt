package app.alexbalan.composecapybara.core.data.ui_datastore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import app.alexbalan.composecapybara.core.data.stage.UiContainer

object UiAnswerMappings {
    val wholeColumnAnswerMappings = mapOf(
        "horizontalAlignment = Alignment.Start" to UiContainer.Column(horizontalAlignment = Alignment.Start),
        "horizontalAlignment = Alignment.End" to UiContainer.Column(horizontalAlignment = Alignment.End),
        "horizontalAlignment = Alignment.CenterHorizontally" to UiContainer.Column(horizontalAlignment = Alignment.CenterHorizontally),
        "verticalArrangement = Arrangement.Top" to UiContainer.Column(verticalArrangement = Arrangement.Top),
        "verticalArrangement = Arrangement.Center" to UiContainer.Column(verticalArrangement = Arrangement.Center),
        "verticalArrangement = Arrangement.Bottom" to UiContainer.Column(verticalArrangement = Arrangement.Bottom),
        "verticalArrangement = Arrangement.SpaceAround" to UiContainer.Column(verticalArrangement = Arrangement.SpaceAround),
        "verticalArrangement = Arrangement.SpaceBetween" to UiContainer.Column(verticalArrangement = Arrangement.SpaceBetween),
        "verticalArrangement = Arrangement.SpaceEvenly" to UiContainer.Column(verticalArrangement = Arrangement.SpaceEvenly)
    )

    val wholeRowAnswerMappings = mapOf(
        "verticalAlignment = Alignment.Top" to UiContainer.Row(verticalAlignment = Alignment.Top),
        "verticalAlignment = Alignment.Bottom" to UiContainer.Row(verticalAlignment = Alignment.Bottom),
        "verticalAlignment = Alignment.CenterVertically" to UiContainer.Row(verticalAlignment = Alignment.CenterVertically),
        "horizontalArrangement = Arrangement.Start" to UiContainer.Row(horizontalArrangement = Arrangement.Start),
        "horizontalArrangement = Arrangement.Center" to UiContainer.Row(horizontalArrangement = Arrangement.Center),
        "horizontalArrangement = Arrangement.End" to UiContainer.Row(horizontalArrangement = Arrangement.End),
        "horizontalArrangement = Arrangement.SpaceAround" to UiContainer.Row(horizontalArrangement = Arrangement.SpaceAround),
        "horizontalArrangement = Arrangement.SpaceBetween" to UiContainer.Row(horizontalArrangement = Arrangement.SpaceBetween),
        "horizontalArrangement = Arrangement.SpaceEvenly" to UiContainer.Row(horizontalArrangement = Arrangement.SpaceEvenly)
    )

    val wholeBoxAnswerMappings = mapOf(
        "contentAlignment = Alignment.TopStart" to UiContainer.Box(contentAlignment = Alignment.TopStart),
        "contentAlignment = Alignment.TopCenter" to UiContainer.Box(contentAlignment = Alignment.TopCenter),
        "contentAlignment = Alignment.TopEnd" to UiContainer.Box(contentAlignment = Alignment.TopEnd),
        "contentAlignment = Alignment.CenterStart" to UiContainer.Box(contentAlignment = Alignment.CenterStart),
        "contentAlignment = Alignment.Center" to UiContainer.Box(contentAlignment = Alignment.Center),
        "contentAlignment = Alignment.CenterEnd" to UiContainer.Box(contentAlignment = Alignment.CenterEnd),
        "contentAlignment = Alignment.BottomStart" to UiContainer.Box(contentAlignment = Alignment.BottomStart),
        "contentAlignment = Alignment.BottomCenter" to UiContainer.Box(contentAlignment = Alignment.BottomCenter),
        "contentAlignment = Alignment.BottomEnd" to UiContainer.Box(contentAlignment = Alignment.BottomEnd)
    )

}
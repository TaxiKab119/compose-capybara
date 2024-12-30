package app.alexbalan.composecapybara.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import app.alexbalan.composecapybara.core.data.stage.FruitType
import app.alexbalan.composecapybara.core.presentation.Capybara

@Composable
fun LevelTester(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Capybara(fruitType = FruitType.STRAWBERRY)
        Capybara(fruitType = FruitType.STRAWBERRY)
        Capybara(fruitType = FruitType.STRAWBERRY)
    }
}

// Test for the size of the level stage
@Preview(device = "spec:width=960dp,height=1080dp,dpi=160")
@Composable
private fun LevelTesterPreview() {
    MaterialTheme {
        LevelTester()
    }
}
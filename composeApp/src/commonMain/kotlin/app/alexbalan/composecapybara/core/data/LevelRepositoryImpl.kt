package app.alexbalan.composecapybara.core.data

import androidx.compose.ui.Alignment
import app.alexbalan.composecapybara.core.data.stage.FruitPosition
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer

class LevelRepositoryImpl : LevelRepository {

    // TODO - This should be replaced with some key-value store
    private val completedLevels = mutableSetOf<Int>()

    private val levels = mapOf(
        0 to LevelConfig(
            preamble = "Preamble: Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Fusce sed erat congue, commodo urna sit amet, venenatis ex. Phasellus egestas, " +
                    "urna ac accumsan aliquet, tortor libero posuere magna, eu tempus neque nisl sit amet nisl. " +
                    "Donec eget aliquam tellus. Aliquam sit amet vehicula tellus. Nullam vel purus vitae nunc semper lobortis " +
                    "eget id turpis. Suspendisse vitae quam at massa venenatis fringilla. Phasellus lobortis ex non lorem sagittis, " +
                    "sed laoreet lacus mollis.",
            instructions = listOf("This is the first instruction", "This is the second instruction", "Here is a third instruction"),
            hints = listOf(),
            initialUserInput = "",
            numUserInputLines = 1,
            existingLinesBefore = listOf("Column ("),
            existingLinesAfter = listOf("\t\tmodifier = modifier", "\t\t\t\t.background(Color.Green)", "\t\t\t\t.fillMaxSize()", ") {", "   \tCaterpillar()", "}"),
            validInput = setOf(
                "horizontalAlignment = Alignment.End",
                "horizontalAlignment = Alignment.Start",
                "horizontalAlignment = Alignment.CenterHorizontally",
                "verticalArrangement = Arrangement.Top",
                "verticalArrangement = Arrangement.Center",
                "verticalArrangement = Arrangement.Bottom",
                "verticalArrangement = Arrangement.SpaceAround",
                "verticalArrangement = Arrangement.SpaceBetween",
                "verticalArrangement = Arrangement.SpaceEvenly"
            ),
            correctAnswer = "horizontalAlignment = Alignment.End",
            stageLayout = StageLayout(
                container = UiContainer.Column(horizontalAlignment = Alignment.End),
                fruit = listOf(FruitPosition.InColumn())
            )
        ),
        1 to LevelConfig(
            preamble = "Preamble: This is a much shorter preamble for the second level.",
            instructions = listOf("This is the first instruction", "Here is a second instruction"),
            hints = listOf(),
            initialUserInput = "",
            numUserInputLines = 1,
            existingLinesBefore = listOf("Column ("),
            existingLinesAfter = listOf("\t\tmodifier = modifier", "\t\t\t\t.background(Color.Green)", "\t\t\t\t.fillMaxSize()", ") {", "   \tCaterpillar()", "}"),
            validInput = setOf(
                "horizontalAlignment = Alignment.End",
                "horizontalAlignment = Alignment.Start",
                "horizontalAlignment = Alignment.CenterHorizontally",
                "verticalArrangement = Arrangement.Top",
                "verticalArrangement = Arrangement.Center",
                "verticalArrangement = Arrangement.Bottom",
                "verticalArrangement = Arrangement.SpaceAround",
                "verticalArrangement = Arrangement.SpaceBetween",
                "verticalArrangement = Arrangement.SpaceEvenly"
            ),
            correctAnswer = "horizontalAlignment = Alignment.End",
            stageLayout = StageLayout(
                container = UiContainer.Column(horizontalAlignment = Alignment.End),
                fruit = listOf(FruitPosition.InColumn())
            )
        )
    )

    override fun getLevelConfig(levelNumber: Int): LevelConfig {
        return levels[levelNumber] ?: levels[0]!!
    }

    override fun isLevelCompleted(levelNumber: Int): Boolean {
        return completedLevels.contains(levelNumber)
    }

    override fun markLevelCompleted(levelNumber: Int) {
        completedLevels.add(levelNumber)
    }
}
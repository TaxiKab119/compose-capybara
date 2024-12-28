package app.alexbalan.composecapybara.core.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import app.alexbalan.composecapybara.core.data.stage.FruitPosition
import app.alexbalan.composecapybara.core.data.stage.FruitType
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer

class LevelRepositoryImpl : LevelRepository {
    private val validColumnInputs = setOf(
        "horizontalAlignment = Alignment.End",
        "horizontalAlignment = Alignment.Start",
        "horizontalAlignment = Alignment.CenterHorizontally",
        "verticalArrangement = Arrangement.Top",
        "verticalArrangement = Arrangement.Center",
        "verticalArrangement = Arrangement.Bottom",
        "verticalArrangement = Arrangement.SpaceAround",
        "verticalArrangement = Arrangement.SpaceBetween",
        "verticalArrangement = Arrangement.SpaceEvenly"
    )

    private val validRowInputs = setOf(
        "horizontalArrangement = Arrangement.End",
        "horizontalArrangement = Arrangement.Start",
        "horizontalArrangement = Arrangement.Center",
        "horizontalArrangement = Arrangement.SpaceAround",
        "horizontalArrangement = Arrangement.SpaceBetween",
        "horizontalArrangement = Arrangement.SpaceEvenly",
        "verticalAlignment = Alignment.Top",
        "verticalAlignment = Alignment.CenterVertically",
        "verticalAlignment = Alignment.Bottom",
    )

    private val validBoxInputs = setOf(
        "Alignment.BottomCenter",
        "Alignment.Center",
        "Alignment.BottomEnd",
        "Alignment.BottomStart",
        "Alignment.CenterEnd",
        "Alignment.CenterStart",
        "Alignment.TopCenter",
        "Alignment.TopEnd",
        "Alignment.TopStart",
    )

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
            instructions = listOf(
                "This is the first instruction",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            initialUserInput = "",
            numUserInputLines = 1,
            existingLinesBefore = listOf("Column ("),
            existingLinesAfter = listOf(
                "\t\tmodifier = modifier",
                "\t\t\t\t.background(Color.Green)",
                "\t\t\t\t.fillMaxSize()",
                ") {",
                "   \tCaterpillar()",
                "}"
            ),
            validInput = validColumnInputs,
            correctAnswer = "horizontalAlignment = Alignment.End",
            stageLayout = StageLayout(
                container = UiContainer.Column(horizontalAlignment = Alignment.End),
                fruit = listOf(FruitPosition.InColumn(FruitType.STRAWBERRY))
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                fruit = listOf(FruitPosition.InColumn(FruitType.STRAWBERRY))
            )
        ),
        1 to LevelConfig(
            preamble = "Preamble: This is a much shorter preamble for the second level.",
            instructions = listOf("This is the first instruction", "Here is a second instruction"),
            hints = listOf(),
            initialUserInput = "",
            numUserInputLines = 1,
            existingLinesBefore = listOf("Column ("),
            existingLinesAfter = listOf(
                "\t\tmodifier = modifier",
                "\t\t\t\t.background(Color.Green)",
                "\t\t\t\t.fillMaxSize()",
                ") {",
                "   \tCaterpillar()",
                "}"
            ),
            validInput = validColumnInputs,
            correctAnswer = "horizontalAlignment = Alignment.CenterHorizontally",
            stageLayout = StageLayout(
                container = UiContainer.Column(horizontalAlignment = Alignment.CenterHorizontally),
                fruit = listOf(
                    FruitPosition.InColumn(FruitType.STRAWBERRY),
                    FruitPosition.InColumn(FruitType.BLUEBERRY)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                fruit = listOf(
                    FruitPosition.InColumn(FruitType.STRAWBERRY),
                    FruitPosition.InColumn(FruitType.BLUEBERRY)
                )
            )
        ),
        2 to LevelConfig(
            preamble = "Preamble: This is a much shorter preamble for the second level.",
            instructions = listOf("This is the first instruction", "Here is a second instruction"),
            hints = listOf(),
            initialUserInput = "",
            numUserInputLines = 1,
            existingLinesBefore = listOf("Column ("),
            existingLinesAfter = listOf(
                "\t\tmodifier = modifier",
                "\t\t\t\t.background(Color.Green)",
                "\t\t\t\t.fillMaxSize()",
                ") {",
                "   \tCaterpillar()",
                "}"
            ),
            validInput = validRowInputs,
            correctAnswer = "horizontalArrangement = Arrangement.Center",
            stageLayout = StageLayout(
                container = UiContainer.Row(horizontalArrangement = Arrangement.Center),
                fruit = listOf(
                    FruitPosition.InRow(FruitType.STRAWBERRY),
                    FruitPosition.InRow(FruitType.BLUEBERRY),
                    FruitPosition.InRow(FruitType.GRAPE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                fruit = listOf(
                    FruitPosition.InRow(FruitType.STRAWBERRY),
                    FruitPosition.InRow(FruitType.BLUEBERRY),
                    FruitPosition.InRow(FruitType.GRAPE)
                )
            )
        ),
        4 to LevelConfig(
            preamble = "Preamble: This is a much shorter preamble for the second level.",
            instructions = listOf("This is the first instruction", "Here is a second instruction"),
            hints = listOf(),
            initialUserInput = "",
            numUserInputLines = 1,
            existingLinesBefore = listOf("Column ("),
            existingLinesAfter = listOf(
                "\t\tmodifier = modifier",
                "\t\t\t\t.background(Color.Green)",
                "\t\t\t\t.fillMaxSize()",
                ") {",
                "   \tCaterpillar()",
                "}"
            ),
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
            stageLayout = null
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
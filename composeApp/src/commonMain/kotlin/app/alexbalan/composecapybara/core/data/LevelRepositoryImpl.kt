package app.alexbalan.composecapybara.core.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import app.alexbalan.composecapybara.core.data.stage.ElementPosition
import app.alexbalan.composecapybara.core.data.stage.FruitType
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer
import app.alexbalan.composecapybara.core.data.ui_datastore.UiAnswerMappings

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
            validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
            correctAnswer = "horizontalAlignment = Alignment.End",
            answerType = AnswerType.COLUMN,
            stageLayout = StageLayout(
                container = UiContainer.Column(horizontalAlignment = Alignment.End),
                elements = listOf(ElementPosition.InColumn(FruitType.STRAWBERRY))
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(ElementPosition.InColumn(FruitType.STRAWBERRY))
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
            validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
            answerType = AnswerType.COLUMN,
            correctAnswer = "horizontalAlignment = Alignment.CenterHorizontally",
            stageLayout = StageLayout(
                container = UiContainer.Column(horizontalAlignment = Alignment.CenterHorizontally),
                elements = listOf(
                    ElementPosition.InColumn(FruitType.STRAWBERRY),
                    ElementPosition.InColumn(FruitType.BLUEBERRY)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(FruitType.STRAWBERRY),
                    ElementPosition.InColumn(FruitType.BLUEBERRY)
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
            validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
            answerType = AnswerType.ROW,
            correctAnswer = "horizontalArrangement = Arrangement.Center",
            stageLayout = StageLayout(
                container = UiContainer.Row(horizontalArrangement = Arrangement.Center),
                elements = listOf(
                    ElementPosition.InRow(FruitType.STRAWBERRY),
                    ElementPosition.InRow(FruitType.BLUEBERRY),
                    ElementPosition.InRow(FruitType.GRAPE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(FruitType.STRAWBERRY),
                    ElementPosition.InRow(FruitType.BLUEBERRY),
                    ElementPosition.InRow(FruitType.GRAPE)
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
            answerType = AnswerType.COLUMN,
            validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
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
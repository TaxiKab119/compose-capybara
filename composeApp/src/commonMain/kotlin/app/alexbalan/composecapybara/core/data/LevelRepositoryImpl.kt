package app.alexbalan.composecapybara.core.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import app.alexbalan.composecapybara.core.data.stage.ElementPosition
import app.alexbalan.composecapybara.core.data.stage.FruitType
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer
import app.alexbalan.composecapybara.core.data.ui_datastore.UiAnswerMappings
import app.alexbalan.composecapybara.core.presentation.CodeFieldState

class LevelRepositoryImpl : LevelRepository {
    // TODO - This should be replaced with some key-value store
    private val completedLevels = mutableSetOf<Int>()

    // TODO - Make sure code field is reflective of # of Capys and their color
    private val levels = mapOf(
        0 to LevelConfig(
            preamble = "Preamble: Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Fusce sed erat congue, commodo urna sit amet, venenatis ex. Phasellus egestas, " +
                    "urna ac accumsan aliquet, tortor libero posuere magna, eu tempus neque nisl sit amet nisl. " +
                    "Donec eget aliquam tellus. Aliquam sit amet vehicula tellus. Nullam vel purus vitae nunc semper lobortis " +
                    "eget id turpis. Suspendisse vitae quam at massa venenatis fringilla. Phasellus lobortis ex non lorem sagittis, " +
                    "sed laoreet lacus mollis.",
            instructions = listOf(
                "Main Axis",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            stageLayout = StageLayout(
                container = UiContainer.Column(verticalArrangement = Arrangement.Bottom),
                elements = listOf(ElementPosition.InColumn(FruitType.STRAWBERRY))
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(ElementPosition.InColumn(FruitType.STRAWBERRY))
            ),
            codeFieldState1 = CodeFieldState(
                userInput = "verticalArrangement = ",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Column (", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                correctAnswer = "verticalArrangement = Arrangement.Bottom",
                answerType = AnswerType.COLUMN,
            )
        ),
        1 to LevelConfig(
            preamble = "Preamble: Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Fusce sed erat congue, commodo urna sit amet, venenatis ex. Phasellus egestas, " +
                    "urna ac accumsan aliquet, tortor libero posuere magna, eu tempus neque nisl sit amet nisl. " +
                    "Donec eget aliquam tellus. Aliquam sit amet vehicula tellus. Nullam vel purus vitae nunc semper lobortis " +
                    "eget id turpis. Suspendisse vitae quam at massa venenatis fringilla. Phasellus lobortis ex non lorem sagittis, " +
                    "sed laoreet lacus mollis.",
            instructions = listOf(
                "Main Axis",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            codeFieldState1 = CodeFieldState(
                userInput = "verticalArrangement = ",
                numUserInputLines = 1,
                existingLinesBefore = listOf(
                    "// This is the Container for the whole level",
                    "Column (",
                    "    modifier = Modifier.fillMaxSize(),"
                ),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                correctAnswer = "verticalArrangement = Arrangement.Center",
                answerType = AnswerType.COLUMN,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(verticalArrangement = Arrangement.Center),
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
            preamble = "Preamble: Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Fusce sed erat congue, commodo urna sit amet, venenatis ex. Phasellus egestas, " +
                    "urna ac accumsan aliquet, tortor libero posuere magna, eu tempus neque nisl sit amet nisl. " +
                    "Donec eget aliquam tellus. Aliquam sit amet vehicula tellus. Nullam vel purus vitae nunc semper lobortis " +
                    "eget id turpis. Suspendisse vitae quam at massa venenatis fringilla. Phasellus lobortis ex non lorem sagittis, " +
                    "sed laoreet lacus mollis.",
            instructions = listOf(
                "Main Axis",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Column (", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                correctAnswer = "verticalArrangement = Arrangement.SpaceBetween",
                answerType = AnswerType.COLUMN,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(verticalArrangement = Arrangement.SpaceBetween),
                elements = listOf(
                    ElementPosition.InColumn(FruitType.STRAWBERRY),
                    ElementPosition.InColumn(FruitType.GRAPE),
                    ElementPosition.InColumn(FruitType.BLUEBERRY),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(FruitType.STRAWBERRY),
                    ElementPosition.InColumn(FruitType.GRAPE),
                    ElementPosition.InColumn(FruitType.BLUEBERRY),
                )
            )
        ),
        3 to LevelConfig(
            preamble = "We can do the same as the last level but using the main axis of a different container. A Row. In a Row, the main axis is the horizontal axis, as opposed to the Column, where the main axis is the vertical axis. Nevertheless, Arrangement is still Arrangement.",
            instructions = listOf(
                "Main Axis",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            codeFieldState1 = CodeFieldState(
                userInput = "horizontalArrangement = ",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Row (", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                correctAnswer = "horizontalArrangement = Arrangement.SpaceBetween",
                answerType = AnswerType.ROW,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(horizontalArrangement = Arrangement.SpaceBetween),
                elements = listOf(
                    ElementPosition.InRow(FruitType.STRAWBERRY),
                    ElementPosition.InRow(FruitType.GRAPE),
                    ElementPosition.InRow(FruitType.BLUEBERRY),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(FruitType.STRAWBERRY),
                    ElementPosition.InRow(FruitType.GRAPE),
                    ElementPosition.InRow(FruitType.BLUEBERRY),
                )
            )
        ),
        4 to LevelConfig(
            preamble = "Let's try some other Arrangements in the Row() container.",
            instructions = listOf(
                "Main Axis",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Row (", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                correctAnswer = "horizontalArrangement = Arrangement.SpaceAround",
                answerType = AnswerType.ROW,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(horizontalArrangement = Arrangement.SpaceAround),
                elements = listOf(
                    ElementPosition.InRow(FruitType.STRAWBERRY),
                    ElementPosition.InRow(FruitType.CARROT),
                    ElementPosition.InRow(FruitType.BLUEBERRY),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(FruitType.STRAWBERRY),
                    ElementPosition.InRow(FruitType.CARROT),
                    ElementPosition.InRow(FruitType.BLUEBERRY),
                )
            )
        ),
        5 to LevelConfig(
            preamble = "Let's try some other Arrangements in the Row() container.",
            instructions = listOf(
                "Main Axis",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Row (", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                correctAnswer = "horizontalArrangement = Arrangement.End",
                answerType = AnswerType.ROW,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(horizontalArrangement = Arrangement.End),
                elements = listOf(
                    ElementPosition.InRow(FruitType.CARROT),
                    ElementPosition.InRow(FruitType.GRAPE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(FruitType.CARROT),
                    ElementPosition.InRow(FruitType.GRAPE),
                )
            )
        ),
        6 to LevelConfig(
            preamble = "What if we want to achieve the same effect as the last level but in a column? This is when we use the cross axis of containers." +
                    "Specifically, you will want to use the Alignment property. Remember that the main axis in a Column is the vertical axis, so the cross axis is the opposite (the horizontal axis).",
            instructions = listOf(
                "Cross Axis",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Column (", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                correctAnswer = "horizontalAlignment = Alignment.End",
                answerType = AnswerType.COLUMN,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(horizontalAlignment = Alignment.End),
                elements = listOf(
                    ElementPosition.InColumn(FruitType.BLUEBERRY),
                    ElementPosition.InColumn(FruitType.GRAPE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(FruitType.BLUEBERRY),
                    ElementPosition.InColumn(FruitType.GRAPE),
                )
            )
        ),
        7 to LevelConfig(
            preamble = "Let's try working with the cross axis in a Row().",
            instructions = listOf(
                "Cross Axis",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Row (", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                correctAnswer = "verticalAlignment = Alignment.CenterVertically",
                answerType = AnswerType.ROW,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(verticalAlignment = Alignment.CenterVertically),
                elements = listOf(
                    ElementPosition.InRow(FruitType.CARROT),
                    ElementPosition.InRow(FruitType.GRAPE),
                    ElementPosition.InRow(FruitType.STRAWBERRY)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(FruitType.CARROT),
                    ElementPosition.InRow(FruitType.GRAPE),
                    ElementPosition.InRow(FruitType.STRAWBERRY)
                )
            )
        ),
        8 to LevelConfig(
            preamble = "Arrangements and Alignment can be combines to make more complex layouts and position your elements anywhere in the 2d plane.",
            instructions = listOf(
                "combination of arrangement and alignment",
                "This is the second instruction",
                "Here is a third instruction"
            ),
            hints = listOf(),
            numUserInputLines = 2,
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Row (", "    modifier = Modifier.fillMaxSize(),"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                correctAnswer = "verticalAlignment = Alignment.CenterVertically",
                answerType = AnswerType.ROW,
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                correctAnswer = "horizontalArrangement = Arrangement.Center"
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(FruitType.GRAPE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(FruitType.GRAPE),
                )
            )
        ),
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
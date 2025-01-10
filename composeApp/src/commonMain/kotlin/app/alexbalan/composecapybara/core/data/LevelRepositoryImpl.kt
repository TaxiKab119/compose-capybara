package app.alexbalan.composecapybara.core.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Column(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
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
                    "Column(",
                    "    modifier = Modifier.fillMaxSize(),"
                ),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Column(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Row(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Row(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Row(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Column(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Row(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Row(", "    modifier = Modifier.fillMaxSize(),"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ),
                elements = listOf(
                    ElementPosition.InRow(FruitType.GRAPE),
                    ElementPosition.InRow(FruitType.CARROT),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(FruitType.GRAPE),
                    ElementPosition.InRow(FruitType.CARROT),
                )
            )
        ),
        9 to LevelConfig(
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
                existingLinesBefore = listOf("// This is the Container for the whole level","Column(", "    modifier = Modifier.fillMaxSize(),"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ),
                elements = listOf(
                    ElementPosition.InColumn(FruitType.GRAPE),
                    ElementPosition.InColumn(FruitType.GRAPE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(FruitType.GRAPE),
                    ElementPosition.InColumn(FruitType.GRAPE)
                )
            )
        ),
        10 to LevelConfig(
            preamble = "Arrangements and Alignment can be combines to make more complex layouts and position your elements anywhere in the 2d plane.",
            instructions = listOf(
                "combination of arrangement and alignment",
                "Center of screen",
                "Here is a third instruction"
            ),
            hints = listOf(),
            numUserInputLines = 2,
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Row(", "    modifier = Modifier.fillMaxSize(),"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ),
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
        11 to LevelConfig(
            preamble = "This layout can also be easily achieved with a Box()",
            instructions = listOf(
                "box content alignment",
                "Center of screen",
                "Here is a third instruction"
            ),
            numUserInputLines = 1,
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Box(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeBoxAnswerMappings.keys,
                answerType = AnswerType.BOX,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.Center),
                elements = listOf(
                    ElementPosition.InBox(FruitType.GRAPE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(FruitType.GRAPE)
                )
            )
        ),
        12 to LevelConfig(
            preamble = "Boxes are configurable to many locations Box()",
            instructions = listOf(
                "box content alignment",
                "bottom end of screen",
                "Here is a third instruction"
            ),
            numUserInputLines = 1,
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Box(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeBoxAnswerMappings.keys,
                answerType = AnswerType.BOX,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.BottomStart),
                elements = listOf(
                    ElementPosition.InBox(FruitType.GRAPE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(FruitType.GRAPE)
                )
            )
        ),
        13 to LevelConfig(
            preamble = "Ok that's well and good, but why use boxes when we can already do these things with Columns and Rows?" +
            "Well, notice that the previous two levels only had one Capybara. Unlike Rows and Columns, when multiple components are placed in a box" +
            "They stack on top of each other. To show this, we've added some offset to the Capys in this example",
            instructions = listOf(
                "box content alignment",
                "Center of screen",
                "Here is a third instruction"
            ),
            numUserInputLines = 1,
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Box(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara(modifier = Modifier.offset(y = 20.dp))","    Capybara(modifier = Modifier.offset(y = 20.dp))", "}"),
                validInput = UiAnswerMappings.wholeBoxAnswerMappings.keys,
                answerType = AnswerType.BOX,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.TopEnd),
                elements = listOf(
                    ElementPosition.InBox(FruitType.GRAPE),
                    ElementPosition.InBox(FruitType.CARROT, Modifier.absoluteOffset(y = 20.dp))
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(FruitType.GRAPE),
                    ElementPosition.InBox(FruitType.CARROT, Modifier.absoluteOffset(y = 20.dp))
                )
            )
        ),
        // TODO - This level is not yet complete
        14 to LevelConfig(
            preamble = "Ok but how can we do configurations where Capybaras need to sleep in weird locations?",
            instructions = listOf(
                "box element align()",
                "Center of screen",
                "Here is a third instruction"
            ),
            numUserInputLines = 1,
            codeFieldState1 = CodeFieldState(
                userInput = "",
                numUserInputLines = 1,
                existingLinesBefore = listOf("// This is the Container for the whole level","Box(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()","    Capybara()", "}"),
                validInput = UiAnswerMappings.wholeBoxAnswerMappings.keys,
                answerType = AnswerType.BOX,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.TopEnd),
                elements = listOf(
                    ElementPosition.InBox(FruitType.GRAPE),
                    ElementPosition.InBox(FruitType.CARROT)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(FruitType.GRAPE),
                    ElementPosition.InBox(FruitType.CARROT)
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
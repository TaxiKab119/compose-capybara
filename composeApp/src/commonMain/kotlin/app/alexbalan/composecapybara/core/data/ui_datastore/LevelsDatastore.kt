package app.alexbalan.composecapybara.core.data.ui_datastore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.alexbalan.composecapybara.core.data.levels.AnswerType
import app.alexbalan.composecapybara.core.data.levels.LevelConfig
import app.alexbalan.composecapybara.core.data.stage.CushionType
import app.alexbalan.composecapybara.core.data.stage.ElementPosition
import app.alexbalan.composecapybara.core.data.stage.StageLayout
import app.alexbalan.composecapybara.core.data.stage.UiContainer
import app.alexbalan.composecapybara.core.presentation.CodeFieldState

object LevelsDatastore {
    val levels = mapOf(
        1 to LevelConfig(
            preamble = "Welcome to Compose Capybara. A game where you write **Jetpack Compose UI** code to help some sleepy capybaras get their much needed shut eye. Guide the capybara to its cushion using the **verticalArrangement** property, which accepts the following values:",
            instructions = LayoutConcept.VERTICAL_ARRANGEMENT.valuesWithDescriptions,
            hints = listOf("For example, **verticalArrangement = Arrangement.Bottom**, will move the capybara to its cushion."),
            stageLayout = StageLayout(
                container = UiContainer.Column(verticalArrangement = Arrangement.Bottom),
                elements = listOf(ElementPosition.InColumn(CushionType.ORANGE))
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(ElementPosition.InColumn(CushionType.ORANGE))
            ),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("// This is the Container for the whole level","Column(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInputs = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
                appendedText = ","
            )
        ),
        2 to LevelConfig(
            preamble = "Great work! Note that these capybaras are picky about their sleeping conditions. They will only sleep if they are on a cushion the same color as they are. Also, since the capybaras are such close friends, they will only sleep if their friends are also happy.",
            hints = listOf("Use **verticalArrangement** to move the capybaras along the **main-axis**. Remember, you can click on highlighted words if you need a refresher."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "Column(",
                    "    modifier = Modifier.fillMaxSize(),"
                ),
                existingLinesAfter = listOf(
                    ") {",
                    "    Capybara(color = Orange)",
                    "    Capybara(color = Blue)"
                    , "}"
                ),
                validInputs = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(verticalArrangement = Arrangement.Center),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.ORANGE),
                    ElementPosition.InColumn(CushionType.BLUE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.ORANGE),
                    ElementPosition.InColumn(CushionType.BLUE)
                )
            )
        ),
        3 to LevelConfig(
            preamble = "You may have noticed from the last level that the elements in a **Column()** are placed vertically, top to bottom (along the **main-axis**).",
            hints = listOf("Use **verticalArrangement** to move the capybaras along the **main-axis** of a Column."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "Column(",
                    "    modifier = Modifier.fillMaxSize(),"
                ),
                existingLinesAfter = listOf(
                    ") {",
                    "    Capybara(color = Orange)",
                    "    Capybara(color = Purple)",
                    "    Capybara(color = Blue)",
                    "}"
                ),
                validInputs = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(verticalArrangement = Arrangement.SpaceBetween),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.ORANGE),
                    ElementPosition.InColumn(CushionType.PURPLE),
                    ElementPosition.InColumn(CushionType.BLUE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.ORANGE),
                    ElementPosition.InColumn(CushionType.PURPLE),
                    ElementPosition.InColumn(CushionType.BLUE),
                )
            )
        ),
        4 to LevelConfig(
            preamble = "Looks like the sleeping arrangements are the same as the last level but using the main-axis of a different container: a **Row()**. In a Row, the main-axis is the horizontal axis, as opposed to the **Column()**, where the main-axis is the vertical axis. Nevertheless, **Arrangement** is still arrangement: arrangement moves capybaras along the main-axis.",
            hints = listOf("Use **horizontalArrangement** to move the capybaras along the **main-axis** in a **Row()**."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("// This is the Container for the whole level","Row(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(
                    ") {",
                    "    Capybara(color = Orange)",
                    "    Capybara(color = Purple)",
                    "    Capybara(color = Blue)",
                    "}"
                ),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(horizontalArrangement = Arrangement.SpaceBetween),
                elements = listOf(
                    ElementPosition.InRow(CushionType.ORANGE),
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.BLUE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(CushionType.ORANGE),
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.BLUE),
                )
            )
        ),
        5 to LevelConfig(
            preamble = "Let's try another arrangement in the **Row()** container. This one looks a lot like the last level, but there is a subtle difference.",
            hints = listOf("Use **horizontalArrangement** to move the capybaras along the **main-axis** in a **Row()**."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Row(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(
                    ") {",
                    "    Capybara(color = Purple)",
                    "    Capybara(color = Orange)",
                    "    Capybara(color = Blue)",
                    "}"
                ),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(horizontalArrangement = Arrangement.SpaceAround),
                elements = listOf(
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.ORANGE),
                    ElementPosition.InRow(CushionType.BLUE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.ORANGE),
                    ElementPosition.InRow(CushionType.BLUE),
                )
            )
        ),
        6 to LevelConfig(
            preamble = "The capybaras insist on one more level with the **Row()** container. It's out of my hands.",
            hints = listOf("Use **horizontalArrangement** to move the capybaras along the **main-axis** in a **Row()**."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Row(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(horizontalArrangement = Arrangement.End),
                elements = listOf(
                    ElementPosition.InRow(CushionType.BLUE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(CushionType.BLUE),
                )
            )
        ),
        7 to LevelConfig(
            preamble = "What if we want to achieve the same effect as the last level but in a **Column()**? This is when we use the **cross-axis** of containers. Specifically, you will want to use the **Alignment** property. Remember that the **main-axis** in a Column is the vertical axis, so the cross-axis is the opposite (the horizontal axis).",
            hints = listOf("Use **horizontalAlignment** to move the capybaras along the **cross-axis** in a **Column()**"),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Column(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInputs = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(horizontalAlignment = Alignment.End),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.BLUE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.BLUE),
                )
            )
        ),
        8 to LevelConfig(
            preamble = "Oh no! The capybaras are stuck in a **Row()** but they need to be aligned vertically. You know what to do.",
            hints = listOf("Use **verticalAlignment** to move the capybaras along the **cross-axis** in a **Row()**"),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Row(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(
                    ") {",
                    "    Capybara(color = Blue)",
                    "    Capybara(color = Purple)",
                    "    Capybara(color = Orange)",
                    "}"
                ),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(verticalAlignment = Alignment.CenterVertically),
                elements = listOf(
                    ElementPosition.InRow(CushionType.BLUE),
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.ORANGE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(CushionType.BLUE),
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.ORANGE)
                )
            )
        ),
        9 to LevelConfig(
            preamble = "**Arrangement** and **Alignment** can be combined to make more complex layouts and position your capybaras anywhere.",
            hints = listOf("Use a combination of **horizontalArrangement** and **verticalAlignment** to complete this level."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Row(", "    modifier = Modifier.fillMaxSize(),"),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                existingLinesAfter = listOf(
                    ") {",
                    "    Capybara(color = Purple)",
                    "    Capybara(color = Orange)",
                    "}"
                ),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ),
                elements = listOf(
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.ORANGE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.ORANGE),
                )
            )
        ),
        10 to LevelConfig(
            preamble = "It seems these capybaras had a disagreement and want to sleep far away from each other. Since they are frustrated, they didn't tell you their color in the code. You should be able to tell which one is which by the container type and their order. Capybaras can be quite argumentative, so this may happen again.",
            hints = listOf("Use a combination of **verticalArrangement** and **horizontalAlignment** to complete this level."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Column(", "    modifier = Modifier.fillMaxSize(),"),
                validInputs = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
                appendedText = ","
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                existingLinesAfter = listOf(
                    ") {",
                    "    Capybara()",
                    "    Capybara()",
                    "}"
                ),
                validInputs = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.PURPLE),
                    ElementPosition.InColumn(CushionType.ORANGE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.PURPLE),
                    ElementPosition.InColumn(CushionType.ORANGE)
                )
            )
        ),
        11 to LevelConfig(
            preamble = "This purple capybara is a little high-maintenance. He wants to sleep right in the middle of the screen. Can you help with that?",
            hints = listOf("Use a combination of **horizontalArrangement** and **verticalAlignment** to complete this level."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Row(", "    modifier = Modifier.fillMaxSize(),"),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ),
                elements = listOf(
                    ElementPosition.InRow(CushionType.PURPLE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(CushionType.PURPLE),
                )
            )
        ),
        12 to LevelConfig(
            preamble = "This blue capybara is also a little high maintenance, but she made our life easier by choosing a **Box()**. A box uses **contentAlignment** at the container level.",
            hints = listOf("Make sure to click on properties you need a refresher on (or ones haven't learned yet)."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("// This is the Container for the whole level","Box(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInputs = UiAnswerMappings.wholeBoxAnswerMappings.keys,
                answerType = AnswerType.BOX,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.Center),
                elements = listOf(
                    ElementPosition.InBox(CushionType.BLUE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(CushionType.BLUE)
                )
            )
        ),
        13 to LevelConfig(
            preamble = "**Box()**-es are configurable to many locations.",
            hints = listOf("Use **contentAlignment** to align the capybara to their cushion."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Box(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(") {", "    Capybara()", "}"),
                validInputs = UiAnswerMappings.wholeBoxAnswerMappings.keys,
                answerType = AnswerType.BOX,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.BottomStart),
                elements = listOf(
                    ElementPosition.InBox(CushionType.ORANGE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(CushionType.ORANGE)
                )
            )
        ),
        14 to LevelConfig(
            preamble = "Ok that's well and good, but why use **Box()** when we can already do these things with **Column()** and **Row()**? Well, notice that the previous two levels only had one Capybara. Unlike Rows and Columns, when multiple components are placed in a box, they stack on top of each other. To show this, the capys have used some **offset**.",
            hints = listOf("Notice the **order** in which the capybaras are stacked and how that relates to the code."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf("Box(", "    modifier = Modifier.fillMaxSize(),"),
                existingLinesAfter = listOf(
                    ") {",
                    "    Capybara(color = Purple)",
                    "    Capybara(modifier = Modifier.offset(y = 20.dp), color = Orange)",
                    "}"
                ),
                validInputs = UiAnswerMappings.wholeBoxAnswerMappings.keys,
                answerType = AnswerType.BOX,
                appendedText = ","
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.TopEnd),
                elements = listOf(
                    ElementPosition.InBox(CushionType.PURPLE),
                    ElementPosition.InBox(CushionType.ORANGE, Modifier.absoluteOffset(y = 20.dp))
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(CushionType.PURPLE),
                    ElementPosition.InBox(CushionType.ORANGE, Modifier.absoluteOffset(y = 20.dp))
                )
            )
        ),
        15 to LevelConfig(
            preamble = "Hold on though. This is 2025. These are some strong independent capybaras. Maybe they don't always want to move in a group based on their parent container. This can be achieved using **Modifier.align()**. Try putting a **Box()** alignment inside the brackets.",
            hints = listOf(
                "If you're wondering where the second capybara is, remember what you learned from the last level about **Box()**.",
                "**Modifier**s are a key concept in Compose Layouts but beyond the scope of these humble capybaras (for now). Consider it your next step after you've finished the game."
            ),
            codeFieldState1 = CodeFieldState(
                userInput = ".align()",
                existingLinesBefore = listOf(
                    "Box(",
                    "    modifier = Modifier.fillMaxSize()",
                    "    contentAlignment = Alignment.TopEnd",
                    ") {",
                    "    Capybara()",
                    "    Capybara("
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf("    )", "}"),
                validInputs = UiAnswerMappings.boxAlignmentMappings.keys,
                answerType = AnswerType.BOX_ALIGN,
                elementIndexToModify = 1
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.TopEnd),
                elements = listOf(
                    ElementPosition.InBox(CushionType.PURPLE),
                    ElementPosition.InBox(CushionType.ORANGE, Modifier, Alignment.BottomEnd)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(contentAlignment = Alignment.TopEnd),
                elements = listOf(
                    ElementPosition.InBox(CushionType.PURPLE),
                    ElementPosition.InBox(CushionType.ORANGE, Modifier)
                )
            )
        ),
        16 to LevelConfig(
            preamble = "Uh oh...looks like these capybaras got in a disagreement again. I know it is only a temporary fix but would you help them sleep as far away as possible from each other.",
            hints = listOf("Use what you learned about **Modifier.align()** from the last level."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "Box(",
                    "    modifier = Modifier.fillMaxSize()",
                    ") {",
                    "    Capybara()",
                    "    Capybara(",
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf("    )"),
                validInputs = UiAnswerMappings.boxAlignmentMappings.keys,
                answerType = AnswerType.BOX_ALIGN,
                elementIndexToModify = 1
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "    Capybara("
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf("    )", "}"),
                validInputs = UiAnswerMappings.boxAlignmentMappings.keys,
                answerType = AnswerType.BOX_ALIGN,
                elementIndexToModify = 2
            ),
            stageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(CushionType.BLUE),
                    ElementPosition.InBox(CushionType.PURPLE, Modifier, Alignment.Center),
                    ElementPosition.InBox(CushionType.ORANGE, Modifier, Alignment.BottomEnd)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Box(),
                elements = listOf(
                    ElementPosition.InBox(CushionType.BLUE),
                    ElementPosition.InBox(CushionType.PURPLE),
                    ElementPosition.InBox(CushionType.ORANGE)
                )
            )
        ),
        17 to LevelConfig(
            preamble = "Let's try arranging the capybaras in different locations within a **Column()**. Remember that we can only align items in along the **cross-axis**",
            hints = listOf("Use some combination of **verticalArrangement**, **horizontalAlignment** and **Modifier.align()** to complete this level."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "Column(",
                    "    modifier = Modifier.fillMaxSize()",
                ),
                existingLinesAfter = listOf(
                    ") {",
                ),
                validInputs = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
                appendedText = ","
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "    Capybara()",
                    "    Capybara(",
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf(
                    "    )",
                    "    Capybara()",
                    "}"
                ),
                validInputs = UiAnswerMappings.columnAlignmentMappings.keys,
                answerType = AnswerType.COL_ALIGN,
                elementIndexToModify = 1
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(verticalArrangement = Arrangement.Bottom),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.ORANGE),
                    ElementPosition.InColumn(CushionType.BLUE, Modifier, Alignment.End),
                    ElementPosition.InColumn(CushionType.PURPLE)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.ORANGE),
                    ElementPosition.InColumn(CushionType.BLUE),
                    ElementPosition.InColumn(CushionType.PURPLE)
                )
            )
        ),
        18 to LevelConfig(
            preamble = "The capybaras have spoken: in order to really understand how **Modifier.align()** works, you should have a level with every parent container.",
            hints = listOf("Use some combination of **horizontalArrangement**, **verticalAlignment** and **Modifier.align()** to complete this level."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "Row(",
                    "    modifier = Modifier.fillMaxSize()",
                    "    verticalAlignment = Alignment.CenterVertically,",
                ),
                existingLinesAfter = listOf(
                    ") {"
                ),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "    Capybara("
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf(
                    "    )",
                    "    Capybara()",
                ),
                validInputs = UiAnswerMappings.rowAlignmentMappings.keys,
                answerType = AnswerType.ROW_ALIGN,
                elementIndexToModify = 0
            ),
            codeFieldState3 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "    Capybara("
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf(
                    "    )",
                    "}"
                ),
                validInputs = UiAnswerMappings.rowAlignmentMappings.keys,
                answerType = AnswerType.ROW_ALIGN,
                elementIndexToModify = 2
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ),
                elements = listOf(
                    ElementPosition.InRow(CushionType.BLUE, Modifier, Alignment.Top),
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.ORANGE, Modifier, Alignment.Bottom),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(
                    verticalAlignment = Alignment.CenterVertically
                ),
                elements = listOf(
                    ElementPosition.InRow(CushionType.BLUE),
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.ORANGE),
                )
            )
        ),
        19 to LevelConfig(
            preamble = "The capybaras have decided to form lines of colors. While I may not understand the decision, it is our duty to serve them. Plus they get really cranky when they're tired...believe me.",
            hints = listOf("Use some combination of **verticalArrangement**, **horizontalAlignment** and **Modifier.align()** to complete this level."),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "Column(",
                    "    modifier = Modifier.fillMaxSize()",
                    "    horizontalAlignment = Alignment.End,",
                ),
                validInputs = UiAnswerMappings.wholeColumnAnswerMappings.keys,
                answerType = AnswerType.COLUMN,
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    ") {",
                    "    Capybara(",
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf("    )"),
                validInputs = UiAnswerMappings.columnAlignmentMappings.keys,
                answerType = AnswerType.COL_ALIGN,
                elementIndexToModify = 0
            ),
            codeFieldState3 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "    Capybara("
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf(
                    "    )",
                    "    Capybara()",
                ),
                validInputs = UiAnswerMappings.columnAlignmentMappings.keys,
                answerType = AnswerType.COL_ALIGN,
                elementIndexToModify = 1
            ),
            codeFieldState4 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "    Capybara("
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf(
                    "    )",
                    "}"
                ),
                validInputs = UiAnswerMappings.columnAlignmentMappings.keys,
                answerType = AnswerType.COL_ALIGN,
                elementIndexToModify = 3
            ),
            stageLayout = StageLayout(
                container = UiContainer.Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.ORANGE, Modifier, Alignment.Start),
                    ElementPosition.InColumn(CushionType.BLUE, Modifier, Alignment.CenterHorizontally),
                    ElementPosition.InColumn(CushionType.PURPLE),
                    ElementPosition.InColumn(CushionType.BLUE,  Modifier, Alignment.CenterHorizontally)
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Column(
                    horizontalAlignment = Alignment.End,
                ),
                elements = listOf(
                    ElementPosition.InColumn(CushionType.ORANGE),
                    ElementPosition.InColumn(CushionType.BLUE),
                    ElementPosition.InColumn(CushionType.PURPLE),
                    ElementPosition.InColumn(CushionType.BLUE),
                )
            )
        ),
        20 to LevelConfig(
            preamble = "The Capybaras are one level away from being completely tuckered out from all the hard work they've been doing (sleeping). Help them get to sleep this last time using all the knowledge you've built so far!",
            hints = listOf("There are **no hints** for this level. You got this!"),
            codeFieldState1 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "// This is the Container for the whole level",
                    "Row(",
                    "    modifier = Modifier.fillMaxSize()",
                ),
                appendedText = ",",
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
            ),
            codeFieldState2 = CodeFieldState(
                userInput = "",
                existingLinesAfter = listOf(
                    ") {"
                ),
                validInputs = UiAnswerMappings.wholeRowAnswerMappings.keys,
                answerType = AnswerType.ROW,
                appendedText = ","
            ),
            codeFieldState3 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "    Capybara()",
                    "    Capybara("
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf(
                    "    )",
                    "    Capybara()",
                ),
                validInputs = UiAnswerMappings.rowAlignmentMappings.keys,
                answerType = AnswerType.ROW_ALIGN,
                elementIndexToModify = 1
            ),
            codeFieldState4 = CodeFieldState(
                userInput = "",
                existingLinesBefore = listOf(
                    "    Capybara("
                ),
                prependedText = "        modifier = Modifier",
                existingLinesAfter = listOf(
                    "    )",
                    "    Capybara()",
                    "}"
                ),
                validInputs = UiAnswerMappings.rowAlignmentMappings.keys,
                answerType = AnswerType.ROW_ALIGN,
                elementIndexToModify = 3
            ),
            stageLayout = StageLayout(
                container = UiContainer.Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceAround
                ),
                elements = listOf(
                    ElementPosition.InRow(CushionType.BLUE),
                    ElementPosition.InRow(CushionType.ORANGE, Modifier, Alignment.Top),
                    ElementPosition.InRow(CushionType.BLUE),
                    ElementPosition.InRow(CushionType.PURPLE, Modifier, Alignment.CenterVertically),
                    ElementPosition.InRow(CushionType.BLUE),
                )
            ),
            initialUserStageLayout = StageLayout(
                container = UiContainer.Row(),
                elements = listOf(
                    ElementPosition.InRow(CushionType.BLUE),
                    ElementPosition.InRow(CushionType.ORANGE),
                    ElementPosition.InRow(CushionType.BLUE),
                    ElementPosition.InRow(CushionType.PURPLE),
                    ElementPosition.InRow(CushionType.BLUE),
                )
            )
        ),
    )
}
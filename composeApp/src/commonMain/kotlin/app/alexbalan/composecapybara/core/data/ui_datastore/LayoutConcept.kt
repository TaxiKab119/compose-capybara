package app.alexbalan.composecapybara.core.data.ui_datastore

enum class LayoutConcept(
    val keyword: String,
    val tag: String,
    val description: String,
    val values: List<String> = listOf(),
    val valuesWithDescriptions: List<String> = listOf()
) {
    COLUMN(
        keyword = "Column()",
        tag = "COL",
        description = "A container whose **main-axis** is vertical, and whose **cross-axis** is horizontal. Elements are arranged top to bottom.",
        values = listOf("verticalArrangement", "horizontalAlignment")
    ),
    ROW(
        keyword = "Row()",
        tag = "ROW",
        description = "A container whose **main-axis** is horizontal, and whose **cross-axis** is vertical. Elements are arranged start (left) to end (right).",
        values = listOf("horizontalArrangement", "verticalAlignment")
    ),
    BOX(
        keyword = "Box()",
        tag = "BOX",
        description = "A container which only uses its own special **Alignment** properties. Elements will overlay on top of each other where the lowest element in the code is at the uppermost layer.",
        values = listOf("contentAlignment")
    ),
    VERTICAL_ARRANGEMENT(
        keyword = "verticalArrangement",
        tag = "V_ARR",
        description = "Controls how items are spaced in a **Column()**'s vertical axis.",
        values = listOf("Top (Default)", "Bottom", "Center", "SpaceAround", "SpaceBetween", "SpaceEvenly"),
        valuesWithDescriptions = listOf(
            "**Top**: items are arranged from the top of the container.",
            "**Bottom**: items are arranged towards the bottom of the container.",
            "**Center**: items are arranged in the center of the container.",
            "**SpaceAround**: items are displayed with equal space around them.",
            "**SpaceBetween**: items are displayed with equal space between them.",
            "**SpaceEvenly**: items are displayed with equal spacing between them and at the edges."
        )
    ),
    MAIN_AXIS(
        keyword = "main-axis",
        tag = "MAIN_AXIS",
        description = "The primary direction of a layout - vertical for **Column**, horizontal for **Row**."
    ),
    HORIZONTAL_ARRANGEMENT(
        keyword = "horizontalArrangement",
        tag = "H_ARR",
        description = "Controls how items are spaced in a **Row()**'s horizontal axis.",
        values = listOf("Start (Default)", "End", "Center", "SpaceAround", "SpaceBetween", "SpaceEvenly"),
        valuesWithDescriptions = listOf(
            "**Start**: items are arranged from the start (left in LTR layouts) of the container.",
            "**End**: items are arranged towards the right (right in LTR layouts) of the container.",
            "**Center**: items are arranged in the center of the container.",
            "**SpaceAround**: items are displayed with equal space around them.",
            "**SpaceBetween**: items are displayed with equal space between them.",
            "**SpaceEvenly**: items are displayed with equal spacing between them and at the edges."
        )
    ),
    CROSS_AXIS(
        keyword = "cross-axis",
        tag = "CROSS_AXIS",
        description = "The perpendicular direction to the **main-axis** - horizontal for **Column**, vertical for **Row**."
    ),
    HORIZONTAL_ALIGNMENT(
        keyword = "horizontalAlignment",
        tag = "H_AL",
        description = "Controls how items are aligned horizontally within their container.",
        values = listOf("Start (Default)", "End", "CenterHorizontally"),
        valuesWithDescriptions = listOf(
            "**Start**: items align to the start (left in LTR layouts) of the container.",
            "**End**: items align to the end (right in LTR layouts) of the container.",
            "**CenterHorizontally**: items align to the horizontal center of the container."
        )
    ),
    VERTICAL_ALIGNMENT(
        keyword = "verticalAlignment",
        tag = "V_AL",
        description = "Controls how items are aligned vertically within their container, aligns elements in the **cross-axis** of the Row",
        values = listOf("Top (Default)", "Bottom", "CenterVertically"),
        valuesWithDescriptions = listOf(
            "**Top**: items are aligned to the top of the container.",
            "**Bottom**: items align to the bottom of the container.",
            "**CenterVertically**: items align to the vertical center of the container.",
        )
    ),
    CONTENT_ALIGNMENT(
        keyword = "contentAlignment",
        tag = "C_AL",
        description = "Controls how content is aligned within a **Box**",
        values = listOf("TopStart (Default)", "TopCenter", "TopEnd", "CenterStart", "Center", "CenterEnd", "BottomStart", "BottomCenter", "BottomEnd")
    ),
    MODIFIER_ALIGN(
        keyword = "Modifier.align()",
        tag = "M_AL",
        description = "Modifies an individual item's alignment within its parent container. Note, while there looks like a lot of possible values, this modifier only uses the **Alignment** values of its container.",
        values = listOf("TopStart", "TopCenter", "TopEnd", "CenterStart", "Center", "CenterEnd", "BottomStart", "BottomCenter", "BottomEnd", "Start", "End", "CenterHorizontally", "Top", "Bottom", "CenterVertically")
    );

    companion object {
        fun fromKeyword(keyword: String): LayoutConcept? =
            entries.find { it.keyword == keyword }
    }
}
package app.alexbalan.composecapybara.core.presentation.components

enum class LayoutConcept(
    val keyword: String,
    val tag: String,
    val description: String,
    val values: List<String> = listOf()
) {
    VERTICAL_ARRANGEMENT(
        keyword = "verticalArrangement",
        tag = "V_ARR",
        description = "Controls how items are spaced in a **Column()**'s vertical axis",
        values = listOf("Top (Default)", "Bottom", "Center", "SpaceAround", "SpaceBetween", "SpaceEvenly")
    ),
    MAIN_AXIS(
        keyword = "main-axis",
        tag = "MAIN_AXIS",
        description = "The primary direction of a layout - vertical for **Column()**, horizontal for **Row()**"
    ),
    HORIZONTAL_ARRANGEMENT(
        keyword = "horizontalArrangement",
        tag = "H_ARR",
        description = "Controls how items are spaced in a **Row()**'s horizontal axis",
        values = listOf("Start (Default)", "End", "Center", "SpaceAround", "SpaceBetween", "SpaceEvenly")
    ),
    CROSS_AXIS(
        keyword = "cross-axis",
        tag = "CROSS_AXIS",
        description = "The perpendicular direction to the **main axis** - horizontal for **Column()**, vertical for **Row*()*"
    ),
    HORIZONTAL_ALIGNMENT(
        keyword = "horizontalAlignment",
        tag = "H_AL",
        description = "Controls how items are aligned horizontally within their container",
        values = listOf("Start (Default)", "End", "CenterHorizontally")
    ),
    VERTICAL_ALIGNMENT(
        keyword = "verticalAlignment",
        tag = "V_AL",
        description = "Controls how items are aligned vertically within their container, aligns elements in the **cross axis** of the Row",
        values = listOf("Top (Default)", "Bottom", "CenterVertically")
    ),
    CONTENT_ALIGNMENT(
        keyword = "contentAlignment",
        tag = "C_AL",
        description = "Controls how content is aligned within a **Box()**",
        values = listOf("TopStart (Default)", "TopCenter", "TopEnd", "CenterStart", "Center", "CenterEnd", "BottomStart", "BottomCenter", "BottomEnd")
    ),
    MODIFIER_ALIGN(
        keyword = "Modifier.align()",
        tag = "M_AL",
        description = "Modifies an individual item's alignment within its parent container. Uses the alignment values of its container."
    );

    companion object {
        fun fromKeyword(keyword: String): LayoutConcept? =
            entries.find { it.keyword == keyword }
    }
}
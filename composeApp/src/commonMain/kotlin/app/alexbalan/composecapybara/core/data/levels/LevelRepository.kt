package app.alexbalan.composecapybara.core.data.levels

interface LevelRepository {
    fun getLevelConfig(levelNumber: Int): LevelConfig
    fun isLevelCompleted(levelNumber: Int): Boolean
    fun markLevelCompleted(levelNumber: Int)
    fun getCompletedLevels(): Set<Int>
    fun getNumberOfLevels(): Int
}
package app.alexbalan.composecapybara.core.data

interface LevelRepository {
    fun getLevelConfig(levelNumber: Int): LevelConfig
    fun isLevelCompleted(levelNumber: Int): Boolean
    fun markLevelCompleted(levelNumber: Int)
}
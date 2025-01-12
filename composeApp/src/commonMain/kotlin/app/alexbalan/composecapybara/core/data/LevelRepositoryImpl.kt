package app.alexbalan.composecapybara.core.data

import app.alexbalan.composecapybara.core.data.ui_datastore.LevelsDatastore

class LevelRepositoryImpl : LevelRepository {
    // TODO - This should be replaced with some key-value store
    private val completedLevels = mutableSetOf<Int>()

    private val levels = LevelsDatastore.levels

    override fun getLevelConfig(levelNumber: Int): LevelConfig {
        return levels[levelNumber] ?: levels[1]!!
    }

    override fun isLevelCompleted(levelNumber: Int): Boolean {
        return completedLevels.contains(levelNumber)
    }

    override fun markLevelCompleted(levelNumber: Int) {
        completedLevels.add(levelNumber)
    }
}
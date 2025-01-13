package app.alexbalan.composecapybara.core.data

import app.alexbalan.composecapybara.core.data.settings.AppSettings
import app.alexbalan.composecapybara.core.data.ui_datastore.LevelsDatastore

class LevelRepositoryImpl(
    private val appSettings: AppSettings
) : LevelRepository {
    private val levels = LevelsDatastore.levels
    override fun getLevelConfig(levelNumber: Int) = levels[levelNumber] ?: LevelConfig()
    override fun isLevelCompleted(levelNumber: Int) = appSettings.isLevelCompleted(levelNumber)
    override fun markLevelCompleted(levelNumber: Int) = appSettings.setLevelCompleted(levelNumber)
    override fun getCompletedLevels() = appSettings.getCompletedLevels()
    override fun getNumberOfLevels() = levels.size
}
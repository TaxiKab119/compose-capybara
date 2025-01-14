package app.alexbalan.composecapybara.core.data.settings

interface SettingsRepository {
    fun getColorBlindMode(): Boolean
    fun setColorBlindMode(enabled: Boolean)
    fun getDifficulty(): GameDifficulty
    fun setDifficulty(difficulty: GameDifficulty)
    fun resetLevelProgress()
}

class SettingsRepositoryImpl(
    private val appSettings: AppSettings
) : SettingsRepository {
    override fun getColorBlindMode(): Boolean = appSettings.isColorblindModeEnabled

    override fun setColorBlindMode(enabled: Boolean) {
        appSettings.isColorblindModeEnabled = enabled
    }

    override fun getDifficulty(): GameDifficulty = appSettings.difficulty

    override fun setDifficulty(difficulty: GameDifficulty) {
        appSettings.difficulty = difficulty
    }

    override fun resetLevelProgress() = appSettings.resetLevelProgress()
}
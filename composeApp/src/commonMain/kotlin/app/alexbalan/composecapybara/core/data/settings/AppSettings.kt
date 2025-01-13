package app.alexbalan.composecapybara.core.data.settings

import com.russhwolf.settings.Settings

class AppSettings(
    private val settings: Settings
) {
    companion object {
        private const val KEY_LEVEL_COMPLETED = "LVL_COMPLETED_"
        private const val KEY_DIFFICULTY = "DIFFICULTY"
        private const val KEY_COLORBLIND_MODE = "COLORBLIND_MODE"
    }

    // Cached set of completed levels
    private var _completedLevels: Set<Int>? = null

    // Initialize cache on first access
    private fun ensureCompletedLevelsLoaded() {
        if (_completedLevels == null) {
            _completedLevels = settings.keys
                .filter { it.startsWith(KEY_LEVEL_COMPLETED) }
                .mapNotNull { key ->
                    if (settings.getBoolean(key, false)) {
                        key.removePrefix(KEY_LEVEL_COMPLETED).toIntOrNull()
                    } else null
                }
                .toSet()
        }
    }

    fun isLevelCompleted(levelNumber: Int): Boolean {
        ensureCompletedLevelsLoaded()
        return _completedLevels?.contains(levelNumber) ?: false
    }

    fun setLevelCompleted(levelNumber: Int) {
        settings.putBoolean("$KEY_LEVEL_COMPLETED$levelNumber", true)
        // Update cache
        _completedLevels = (_completedLevels ?: emptySet()) + levelNumber
    }

    fun getCompletedLevels(): Set<Int> {
        ensureCompletedLevelsLoaded()
        return _completedLevels ?: emptySet()
    }

    // Difficulty
    var difficulty: GameDifficulty
        get() = GameDifficulty.valueOf(
            settings.getString(KEY_DIFFICULTY, GameDifficulty.NORMAL.name)
        )
        set(value) = settings.putString(KEY_DIFFICULTY, value.name)

    // Colorblind mode
    var isColorblindModeEnabled: Boolean
        get() = settings.getBoolean(KEY_COLORBLIND_MODE, false)
        set(value) = settings.putBoolean(KEY_COLORBLIND_MODE, value)
}

enum class GameDifficulty {
    EASY, NORMAL, HARD
}
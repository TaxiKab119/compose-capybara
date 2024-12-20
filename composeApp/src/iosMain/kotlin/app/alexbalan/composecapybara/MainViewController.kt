package app.alexbalan.composecapybara

import androidx.compose.ui.window.ComposeUIViewController
import app.alexbalan.composecapybara.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
package app.alexbalan.composecapybara

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.alexbalan.composecapybara.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "ComposeCapybara",
        ) {
            App()
        }
    }
}


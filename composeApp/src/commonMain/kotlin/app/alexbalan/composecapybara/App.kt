package app.alexbalan.composecapybara

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import app.alexbalan.composecapybara.navigation.Level
import app.alexbalan.composecapybara.core.presentation.GameScreenRoot
import app.alexbalan.composecapybara.core.presentation.LevelViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(navController, startDestination = Level(1)) {
            composable<Level> { navBackStackEntry ->
                val level: Level = navBackStackEntry.toRoute()
                val viewModel: LevelViewModel = koinViewModel { parametersOf(level.number) }
                GameScreenRoot(
                    viewModel = viewModel,
                    onForwardClick = { navController.navigate(route = Level(it + 1)) },
                    onBackwardClick = {
                        if (it != 1) { navController.navigate(route = Level(it - 1)) }
                    },
                    onTextUpdated1 = { viewModel.updateUserInput(it, 1) },
                    onTextUpdated2 = { viewModel.updateUserInput(it, 2) },
                    onTextUpdated3 = { viewModel.updateUserInput(it, 3) },
                    onTextUpdated4 = { viewModel.updateUserInput(it, 4) },
                )
            }
        }
    }
}
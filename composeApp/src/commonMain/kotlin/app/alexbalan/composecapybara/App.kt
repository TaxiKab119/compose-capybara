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

        NavHost(navController, startDestination = Level(0)) {
            composable<Level> { navBackStackEntry ->
                val level: Level = navBackStackEntry.toRoute()
                val viewModel: LevelViewModel = koinViewModel {
                    parametersOf(level.number)
                }
                GameScreenRoot(
                    viewModel = viewModel,
                    onForwardClick = { navController.navigate(route = Level(it + 1)) },
                    onBackwardClick = {
                        if (it != 0) { navController.navigate(route = Level(it - 1)) }
                    },
                    onTextUpdated = { viewModel.updateUserInput(it) }
                )
            }
        }
    }
}
package app.alexbalan.composecapybara.di

import app.alexbalan.composecapybara.core.presentation.LevelViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {
    viewModel { LevelViewModel(get()) }
}
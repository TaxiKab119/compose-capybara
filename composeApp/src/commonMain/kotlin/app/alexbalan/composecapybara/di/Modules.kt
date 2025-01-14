package app.alexbalan.composecapybara.di

import app.alexbalan.composecapybara.core.data.levels.LevelRepository
import app.alexbalan.composecapybara.core.data.levels.LevelRepositoryImpl
import app.alexbalan.composecapybara.core.data.settings.AppSettings
import app.alexbalan.composecapybara.core.data.settings.SettingsRepository
import app.alexbalan.composecapybara.core.data.settings.SettingsRepositoryImpl
import app.alexbalan.composecapybara.core.presentation.LevelViewModel
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {
    single<Settings> { Settings() }
    single { AppSettings(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<LevelRepository> { LevelRepositoryImpl(get()) }
    viewModel { LevelViewModel(get(), get(), get()) }
}
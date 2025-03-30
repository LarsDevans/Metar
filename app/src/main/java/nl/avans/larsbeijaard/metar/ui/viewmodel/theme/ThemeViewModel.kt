package nl.avans.larsbeijaard.metar.ui.viewmodel.theme

import nl.avans.larsbeijaard.metar.data.DarkModePreferences
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatDelegate

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val darkModePreferences = DarkModePreferences(application)

    private val _uiState = MutableStateFlow(ThemeUiState())
    val uiState: StateFlow<ThemeUiState> = _uiState.asStateFlow()

    init {
        loadThemePreference()
    }

    fun toggleTheme() {
        val isDarkTheme = !_uiState.value.isDarkTheme
        updateThemeState(isDarkTheme)
        saveThemePreference(isDarkTheme)
    }

    private fun loadThemePreference() {
        viewModelScope.launch {
            darkModePreferences.darkModeFlow.collect { isDark ->
                updateThemeState(isDark)
                AppCompatDelegate.setDefaultNightMode(
                    if (isDark) AppCompatDelegate.MODE_NIGHT_YES
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    private fun updateThemeState(isDarkTheme: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isDarkTheme = isDarkTheme
            )
        }
    }

    private fun saveThemePreference(isDarkTheme: Boolean) {
        viewModelScope.launch {
            darkModePreferences.saveDarkMode(isDarkTheme)
        }
    }
}
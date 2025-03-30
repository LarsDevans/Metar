package nl.avans.larsbeijaard.metar.ui.viewmodel.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ThemeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ThemeUiState())
    val uiState: StateFlow<ThemeUiState> = _uiState.asStateFlow()

    fun toggleTheme() {
        val isDarkTheme = !_uiState.value.isDarkTheme
        updateThemeState(isDarkTheme)
    }

    private fun updateThemeState(isDarkTheme: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isDarkTheme = isDarkTheme
            )
        }
    }
}
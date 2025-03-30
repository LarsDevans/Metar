package nl.avans.larsbeijaard.metar

import nl.avans.larsbeijaard.metar.ui.component.TopBar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import nl.avans.larsbeijaard.metar.ui.component.Avatar
import nl.avans.larsbeijaard.metar.ui.theme.MetarTheme
import nl.avans.larsbeijaard.metar.ui.viewmodel.theme.ThemeUiState
import nl.avans.larsbeijaard.metar.ui.viewmodel.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeUiState: ThemeUiState by themeViewModel.uiState.collectAsState()
            MetarTheme(darkTheme = themeUiState.isDarkTheme) {
                Scaffold(
                    topBar = { TopBar() },
                    modifier = Modifier.fillMaxSize(),
                    content = { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Avatar(
                                modifier = Modifier.fillMaxWidth(fraction = 0.8f)
                            )
                        }
                    }
                )
            }
        }
    }
}
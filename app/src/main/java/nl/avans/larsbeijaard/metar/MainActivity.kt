package nl.avans.larsbeijaard.metar

import nl.avans.larsbeijaard.metar.ui.TopBar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import nl.avans.larsbeijaard.metar.ui.theme.MetarTheme
import nl.avans.larsbeijaard.metar.ui.darkmode.ThemeUiState
import nl.avans.larsbeijaard.metar.ui.darkmode.ThemeViewModel
import nl.avans.larsbeijaard.metar.ui.navigation.MetarNavHost

class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val themeUiState: ThemeUiState by themeViewModel.uiState.collectAsState()

            MetarTheme(darkTheme = themeUiState.isDarkTheme) {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.padding(
            WindowInsets.systemBars.asPaddingValues()
        ),
        topBar = { TopBar() }
    ) {
        // Account for the TopBar height by adding a padding at the top
        Box(modifier = Modifier
            .padding(top = 64.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            MetarNavHost(navController = rememberNavController())
        }
    }
}
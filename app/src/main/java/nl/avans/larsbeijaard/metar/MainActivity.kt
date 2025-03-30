package nl.avans.larsbeijaard.metar

import nl.avans.larsbeijaard.metar.ui.component.TopBar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nl.avans.larsbeijaard.metar.ui.component.Avatar
import nl.avans.larsbeijaard.metar.ui.component.TextInput
import nl.avans.larsbeijaard.metar.ui.theme.MetarTheme
import nl.avans.larsbeijaard.metar.ui.viewmodel.avatar.AvatarUiState
import nl.avans.larsbeijaard.metar.ui.viewmodel.avatar.AvatarViewModel
import nl.avans.larsbeijaard.metar.ui.viewmodel.theme.ThemeUiState
import nl.avans.larsbeijaard.metar.ui.viewmodel.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()
    private val avatarViewModel: AvatarViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeUiState: ThemeUiState by themeViewModel.uiState.collectAsState()
            val avatarUiState: AvatarUiState by avatarViewModel.uiState.collectAsState()
            MetarTheme(darkTheme = themeUiState.isDarkTheme) {
                Scaffold(
                    topBar = { TopBar() },
                    modifier = Modifier.fillMaxSize(),
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(paddingValues),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            TextInput(
                                label = stringResource(R.string.username),
                                value = avatarUiState.username,
                                placeholder = stringResource(R.string.username_placeholder),
                                onValueChange = { avatarViewModel.updateUsername(it) }
                            )
                            Avatar(
                                modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                                viewModel = avatarViewModel
                            )
                        }
                    }
                )
            }
        }
    }
}
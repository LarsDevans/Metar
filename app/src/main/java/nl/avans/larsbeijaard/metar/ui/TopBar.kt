package nl.avans.larsbeijaard.metar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.ui.icons.Dark_mode
import nl.avans.larsbeijaard.metar.ui.icons.Light_mode
import nl.avans.larsbeijaard.metar.ui.viewmodel.theme.ThemeViewModel

@Composable
fun TopBar(themeViewModel: ThemeViewModel = viewModel()) {
    Surface(
        modifier = Modifier.height(64.dp)
        // Ignore tonal elevation; no scrolling expected.
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Headline()
            TrailingInteractiveIcons(themeViewModel)
        }
    }
}

@Composable
private fun Headline() {
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun TrailingInteractiveIcons(themeViewModel: ThemeViewModel) {
    val themeUiState by themeViewModel.uiState.collectAsState()

    // Avoid default Material 3 design; unexpected padding was misattributed to it.
    IconButton(
        onClick = { themeViewModel.toggleTheme() },
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = if (themeUiState.isDarkTheme) Light_mode else Dark_mode,
            contentDescription = stringResource(R.string.dark_light_mode)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}
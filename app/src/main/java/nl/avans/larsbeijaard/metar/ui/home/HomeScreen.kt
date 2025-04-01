package nl.avans.larsbeijaard.metar.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.data.avatar.toApiString
import nl.avans.larsbeijaard.metar.data.avatar.toLocalizedGenderString
import nl.avans.larsbeijaard.metar.ui.AppViewModelProvider
import nl.avans.larsbeijaard.metar.ui.component.Avatar
import nl.avans.larsbeijaard.metar.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route: String = "home"
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val (username, genderType) = uiState.value

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { viewModel.updateUsername(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text(stringResource(R.string.username)) },
            placeholder = { Text(stringResource(R.string.username_placeholder)) },
            singleLine = true
        )

        GenderDropdownMenu(
            selected = genderType.toLocalizedGenderString(LocalContext.current),
            onSelectedChange = { viewModel.updateGender(it) }
        )

        Avatar(
            model = "https://avatar.iran.liara.run/public/${genderType.toApiString()}?username=$username",
            modifier = Modifier.fillMaxWidth(0.8f)
        )
    }
}
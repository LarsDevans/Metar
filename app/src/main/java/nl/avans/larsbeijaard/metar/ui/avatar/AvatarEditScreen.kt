package nl.avans.larsbeijaard.metar.ui.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.data.avatar.toApiString
import nl.avans.larsbeijaard.metar.data.avatar.toLocalizedGenderString
import nl.avans.larsbeijaard.metar.data.service.getAvatarUrl
import nl.avans.larsbeijaard.metar.ui.AppViewModelProvider
import nl.avans.larsbeijaard.metar.ui.component.Avatar
import nl.avans.larsbeijaard.metar.ui.home.GenderDropdownMenu
import nl.avans.larsbeijaard.metar.ui.navigation.NavigationDestination

object AvatarEditDestination : NavigationDestination {
    override val route: String = "avatar_edit"

    const val AVATAR_ID_ARG = "avatarId"
    val routeWithArgs = "$route/{$AVATAR_ID_ARG}"
}

@Composable
fun AvatarEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AvatarEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = viewModel.uiState.username,
            style = MaterialTheme.typography.headlineLarge
        )

        Avatar(
            model = getAvatarUrl(
                username = viewModel.uiState.username,
                gender = viewModel.uiState.genderType.toApiString()
            )
        )

        UsernameInput(
            username = viewModel.uiState.username,
            onUsernameChange = { viewModel.updateUsername(it) }
        )

        GenderDropdownMenu(
            selected = viewModel.uiState.genderType.toLocalizedGenderString(LocalContext.current),
            onSelectedChange = { viewModel.updateGender(it) }
        )

        UseInitialsToggle(
            useInitials = false,
            onUseInitialsChange = { }
        )

        ActionButtons(
            onCancel = navigateBack,
            onDownload = { },
            onSave = {
                viewModel.saveAvatarChanges()
                navigateBack()
            }
        )
    }
}

@Composable
fun UsernameInput(username: String, onUsernameChange: (String) -> Unit) {
    TextField(
        value = username,
        onValueChange = onUsernameChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.username)) },
        placeholder = { Text(stringResource(R.string.username_placeholder)) },
        singleLine = true
    )
}

@Composable
fun UseInitialsToggle(useInitials: Boolean, onUseInitialsChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(R.string.use_initials))
        Switch(
            checked = useInitials,
            onCheckedChange = onUseInitialsChange
        )
    }
}

@Composable
fun ActionButtons(onCancel: () -> Unit, onDownload: () -> Unit, onSave: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedButton(onClick = onCancel) {
            Text(text = stringResource(R.string.cancel))
        }
        OutlinedButton(onClick = onDownload) {
            Text(text = stringResource(R.string.download))
        }
        Button(onClick = onSave) {
            Text(text = stringResource(R.string.save))
        }
    }
}
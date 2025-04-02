package nl.avans.larsbeijaard.metar.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import nl.avans.larsbeijaard.metar.data.avatar.Avatar
import nl.avans.larsbeijaard.metar.data.avatar.GenderType
import nl.avans.larsbeijaard.metar.data.avatar.toApiString
import nl.avans.larsbeijaard.metar.data.avatar.toLocalizedGenderString
import nl.avans.larsbeijaard.metar.data.service.getAvatarUrl
import nl.avans.larsbeijaard.metar.ui.AppViewModelProvider
import nl.avans.larsbeijaard.metar.ui.component.Avatar
import nl.avans.larsbeijaard.metar.ui.navigation.NavigationDestination
import kotlin.reflect.KFunction1

object HomeDestination : NavigationDestination {
    override val route: String = "home"
}

@Composable
fun HomeScreen(
    navigateToAvatarEdit: (Int) -> Unit,
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
        UsernameInput(
            username = username,
            onUsernameChange = { viewModel.updateUsername(it) }
        )
        GenderSelection(
            genderType =  genderType,
            onGenderChange = { viewModel.updateGender(it) }
        )
        AvatarDisplay(
            username = username,
            genderType = genderType
        )
        ActionButtons(onSave = { viewModel.saveAvatar() })
        AvatarList(
            navigateToAvatarEdit = navigateToAvatarEdit,
            avatarList = uiState.value.avatarList,
            viewModel =  viewModel
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
fun GenderSelection(genderType: GenderType, onGenderChange: (String) -> Unit) {
    GenderDropdownMenu(
        selected = genderType.toLocalizedGenderString(LocalContext.current),
        onSelectedChange = onGenderChange
    )
}

@Composable
fun AvatarDisplay(username: String, genderType: GenderType) {
    Avatar(
        model = getAvatarUrl(
            username = username,
            gender = genderType.toApiString()
        ),
        modifier = Modifier.fillMaxWidth(0.8f)
    )
}

@Composable
fun ActionButtons(onSave: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedButton(onClick = onSave) {
            Text(text = stringResource(R.string.save))
        }
        Button(onClick = {}) {
            Text(text = stringResource(R.string.download))
        }
    }
}

@Composable
fun AvatarList(navigateToAvatarEdit: (Int) -> Unit, avatarList: List<Avatar>, viewModel: HomeViewModel) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        items(avatarList) { avatar ->
            val model = getAvatarUrl(
                username = avatar.username,
                gender = avatar.gender
            )
            AvatarShowcase(
                username = avatar.username.ifEmpty { stringResource(R.string.anonymous) },
                model = model,
                modifier = Modifier,
                onDownload = {},
                onEdit = { navigateToAvatarEdit(avatar.id) },
                onDelete = { viewModel.deleteAvatar(avatar) }
            )
        }
    }
}
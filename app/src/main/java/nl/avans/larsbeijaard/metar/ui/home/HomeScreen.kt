package nl.avans.larsbeijaard.metar.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalConfiguration
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

object HomeDestination : NavigationDestination {
    override val route: String = "home"
}

@Composable
fun HomeScreen(
    navigateToAvatarEdit: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LandscapeHomeScreen(
            navigateToAvatarEdit = navigateToAvatarEdit,
            modifier = modifier,
            viewModel = viewModel
        )
    } else {
        PortraitHomeScreen(
            navigateToAvatarEdit = navigateToAvatarEdit,
            modifier = modifier,
            viewModel = viewModel
        )
    }
}

@Composable
fun LandscapeHomeScreen(
    navigateToAvatarEdit: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    val (username, genderType) = uiState.value

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = modifier.fillMaxWidth().weight(1f),
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
            }

            Column(
                modifier = modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    alignment = Alignment.CenterVertically,
                    space = 16.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AvatarDisplay(
                    username = username,
                    genderType = genderType,
                    modifier.fillMaxSize(fraction = 0.5f)
                )
                ActionButtons(
                    onSave = { viewModel.saveAvatar() },
                    onDownload = { viewModel.downloadAvatar() }
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AvatarList(
                navigateToAvatarEdit = navigateToAvatarEdit,
                avatarList = uiState.value.avatarList,
                viewModel =  viewModel
            )
        }
    }
}

@Composable
fun PortraitHomeScreen(
    navigateToAvatarEdit: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
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
        ActionButtons(
            onSave = { viewModel.saveAvatar() },
            onDownload = { viewModel.downloadAvatar() }
        )
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
fun AvatarDisplay(username: String, genderType: GenderType, modifier: Modifier = Modifier) {
    Avatar(
        model = getAvatarUrl(
            username = username,
            gender = genderType.toApiString()
        ),
        modifier = modifier.fillMaxWidth(0.8f)
    )
}

@Composable
fun ActionButtons(onSave: () -> Unit, onDownload: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedButton(onClick = onSave) {
            Text(text = stringResource(R.string.save))
        }
        Button(onClick = onDownload) {
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
                onDownload = { viewModel.downloadAvatar(avatar) },
                onEdit = { navigateToAvatarEdit(avatar.id) },
                onDelete = { viewModel.deleteAvatar(avatar) }
            )
        }
    }
}
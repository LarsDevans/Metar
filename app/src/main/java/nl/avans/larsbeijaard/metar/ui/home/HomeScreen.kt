package nl.avans.larsbeijaard.metar.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.data.constant.getAllGenderTypes
import nl.avans.larsbeijaard.metar.ui.component.Avatar
import nl.avans.larsbeijaard.metar.ui.component.DropdownMenu
import nl.avans.larsbeijaard.metar.ui.component.TextInput
import nl.avans.larsbeijaard.metar.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route: String = "home"
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInput(
            label = stringResource(R.string.username),
            placeholder = stringResource(R.string.username_placeholder),
            onValueChange = { }
        )

        DropdownMenu(
            options = getAllGenderTypes(context = LocalContext.current),
            selected = "",
            onSelectedChange = { },
            onValueChange = { }
        )

        Avatar(
            modifier = Modifier.fillMaxWidth(fraction = 0.8f)
        )
    }
}
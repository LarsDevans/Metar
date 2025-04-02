package nl.avans.larsbeijaard.metar.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.ui.component.Avatar

@Composable
fun AvatarShowcase(
    username: String,
    model: String,
    modifier: Modifier = Modifier,
    onDownload: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.padding(8.dp).width(160.dp).height(200.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AvatarContent(username, model, modifier)
            AvatarMenu(expanded, { expanded = it }, onDownload, onEdit, onDelete)
        }
    }
}

@Composable
fun AvatarContent(username: String, model: String, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            alignment = Alignment.CenterVertically, space = 8.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Avatar(
            model = model,
            modifier = modifier
        )
        Text(
            text = username,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun BoxScope.AvatarMenu(
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onDownload: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    IconButton(
        onClick = { onExpandChange(true) },
        modifier = Modifier.align(Alignment.TopEnd)
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Menu"
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onExpandChange(false) }
    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(R.string.download)) },
            onClick = {
                onExpandChange(false)
                onDownload()
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(R.string.edit)) },
            onClick = {
                onExpandChange(false)
                onEdit()
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(R.string.delete)) },
            onClick = {
                onExpandChange(false)
                onDelete()
            }
        )
    }
}
package nl.avans.larsbeijaard.metar.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.data.avatar.getAllGenderTypes
import nl.avans.larsbeijaard.metar.data.avatar.toApiString
import nl.avans.larsbeijaard.metar.data.avatar.toLocalizedGenderString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDropdownMenu(
    selected: String,
    onSelectedChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = selected,
            onValueChange = {}, // It's read-only
            readOnly = true,
            label = { Text(stringResource(R.string.gender)) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = stringResource(R.string.dropdown_menu_arrow)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    type = MenuAnchorType.PrimaryEditable,
                    enabled = true
                )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            getAllGenderTypes().forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(option.toLocalizedGenderString(LocalContext.current))
                    },
                    onClick = {
                        onSelectedChange(option.toApiString())
                        expanded = false
                    }
                )
            }
        }
    }
}
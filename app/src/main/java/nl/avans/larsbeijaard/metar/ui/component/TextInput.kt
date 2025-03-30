package nl.avans.larsbeijaard.metar.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.avans.larsbeijaard.metar.R

@Composable
fun TextInput(
    label: String,
    value: String = "",
    placeholder: String = "",
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun TextInputPreview() {
    TextInput(
        stringResource(R.string.username),
        onValueChange = {}
    )
}
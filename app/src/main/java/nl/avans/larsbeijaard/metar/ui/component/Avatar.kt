package nl.avans.larsbeijaard.metar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import nl.avans.larsbeijaard.metar.R

@Composable
fun Avatar(
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = "https://avatar.iran.liara.run/public",
        contentDescription = stringResource(R.string.avatar_image),
        loading = { CircularProgressIndicator() },
        error = {
            Image(
                painterResource(R.drawable.error_image),
                contentDescription = null
            )
        },
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}
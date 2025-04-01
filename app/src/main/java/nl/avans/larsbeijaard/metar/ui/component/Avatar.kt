package nl.avans.larsbeijaard.metar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import nl.avans.larsbeijaard.metar.R

@Composable
fun Avatar(
    model: String,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = model,
        contentDescription = stringResource(R.string.avatar_image),
        modifier = modifier.fillMaxWidth().aspectRatio(1f),
        loading = { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) },
        error = {
            Image(
                painter = painterResource(R.drawable.error_image),
                contentDescription = stringResource(R.string.error_image_description),
                modifier = Modifier.fillMaxWidth()
            )
        },
        contentScale = ContentScale.Crop
    )
}
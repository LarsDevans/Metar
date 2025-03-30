package nl.avans.larsbeijaard.metar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.ui.viewmodel.avatar.AvatarViewModel

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    viewModel: AvatarViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uiState.avatarUrl)
            .error(R.drawable.error_image)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = stringResource(R.string.avatar_image),
        modifier = modifier,
        contentScale = ContentScale.FillWidth
    )
}
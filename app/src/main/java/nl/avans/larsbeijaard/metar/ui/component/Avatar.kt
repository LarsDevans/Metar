package nl.avans.larsbeijaard.metar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.ui.viewmodel.avatar.AvatarViewModel

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    viewModel: AvatarViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier) {
        SubcomposeAsyncImage(
            model = uiState.avatarUrl,
            contentDescription = stringResource(R.string.avatar_image),
            loading = { CircularProgressIndicator() },
            error = {
                Image(
                    painterResource(R.drawable.error_image),
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}
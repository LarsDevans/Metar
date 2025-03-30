package nl.avans.larsbeijaard.metar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import nl.avans.larsbeijaard.metar.R

@Composable
fun Avatar(username: String = "linde", size: Int = 300) {
    val imageUrl = "https://avatar.iran.liara.run/public/girl?username=$username"
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build()
    )

    Surface(
        modifier = Modifier.size(size.dp),
        shape = CircleShape,
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.avatar_image),
            modifier = Modifier.clip(CircleShape)
        )
    }
}

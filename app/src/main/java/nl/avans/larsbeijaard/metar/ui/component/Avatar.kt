package nl.avans.larsbeijaard.metar.ui.component

import android.util.Log
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
import nl.avans.larsbeijaard.metar.data.enums.GenderType
import nl.avans.larsbeijaard.metar.data.utilities.getAvatarUrl

@Composable
fun Avatar(username: String = "linde", size: Int = 300, gender: GenderType = GenderType.GIRL) {
    val imageUrl = getAvatarUrl(username, gender)
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .listener(
                onError = { _, result ->
                    Log.e("Avatar", "Error loading image: $result")
                },
            )
            .error(R.drawable.error_image)
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

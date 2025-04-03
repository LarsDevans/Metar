package nl.avans.larsbeijaard.metar.ui

import android.Manifest.permission
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import nl.avans.larsbeijaard.metar.R
import nl.avans.larsbeijaard.metar.ui.component.Avatar
import nl.avans.larsbeijaard.metar.ui.darkmode.ThemeViewModel
import nl.avans.larsbeijaard.metar.ui.icons.Dark_mode
import nl.avans.larsbeijaard.metar.ui.icons.Emoticon
import nl.avans.larsbeijaard.metar.ui.icons.Light_mode
import nl.avans.larsbeijaard.metar.ui.icons.Photo_camera
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus

@Composable
fun TopBar(themeViewModel: ThemeViewModel = viewModel()) {
    Surface(
        modifier = Modifier.height(64.dp)
        // Ignore tonal elevation; no scrolling expected.
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Headline()
            TrailingInteractiveIcons(themeViewModel)
        }
    }
}

@Composable
private fun Headline() {
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun TrailingInteractiveIcons(themeViewModel: ThemeViewModel) {
    val themeUiState by themeViewModel.uiState.collectAsState()

    Row(
        horizontalArrangement = Arrangement.spacedBy(alignment = Alignment.End, space = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FavoriteAvatarPicker()
        CameraOpener()

        // Avoid default Material 3 design; unexpected padding was misattributed to it.
        IconButton(
            onClick = { themeViewModel.toggleTheme() },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = if (themeUiState.isDarkTheme) Light_mode else Dark_mode,
                contentDescription = stringResource(R.string.dark_light_mode)
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraOpener() {
    val uriState: MutableState<Uri?> = remember { mutableStateOf(null) }
    val cameraPermission = rememberPermissionState(permission = permission.CAMERA)
    val launcher = createCameraLauncher(uriState)

    HandleCameraPermission(cameraPermission, launcher)
}

@Composable
private fun createCameraLauncher(uriState: MutableState<Uri?>) =
    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                uriState.value = uri
            }
        }
    }

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun HandleCameraPermission(cameraPermission: PermissionState, launcher: ActivityResultLauncher<Intent>) {
    when (cameraPermission.status) {
        PermissionStatus.Granted -> {
            CameraIconButton { launchCamera(launcher) }
        }
        is PermissionStatus.Denied -> {
            CameraIconButton { cameraPermission.launchPermissionRequest() }
        }
    }
}

@Composable
private fun CameraIconButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Photo_camera,
            contentDescription = stringResource(R.string.camera_opener)
        )
    }
}

private fun launchCamera(launcher: ActivityResultLauncher<Intent>) {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    launcher.launch(intent)
}

@Composable
fun FavoriteAvatarPicker() {
    val uriState: MutableState<Uri?> = remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                uriState.value = uri
            }
        }
    }

    ImageFromUri(uriState.value)

    // Avoid default Material 3 design; unexpected padding was misattributed to it.
    IconButton(
        onClick = {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            launcher.launch(intent)
        },
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Emoticon,
            contentDescription = stringResource(R.string.favorite_avatar)
        )
    }
}

@Composable
fun ImageFromUri(uri: Uri?) {
    if (uri != null) {
        Avatar(
            model = uri.toString(),
            modifier = Modifier.size(40.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}
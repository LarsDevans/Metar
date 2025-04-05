package nl.avans.larsbeijaard.metar.ui.camera

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus

class CameraViewModel : ViewModel() {
    @OptIn(ExperimentalPermissionsApi::class)
    lateinit var cameraPermission: PermissionState
    lateinit var launcher: ActivityResultLauncher<Intent>

    @OptIn(ExperimentalPermissionsApi::class)
    fun handleCameraPermission() {
        when (cameraPermission.status) {
            PermissionStatus.Granted -> {
                launchCamera()
            }
            is PermissionStatus.Denied -> {
                cameraPermission.launchPermissionRequest()
            }
        }
    }

    fun createCameraLauncher(launcher: ActivityResultLauncher<Intent>) {
        this.launcher = launcher
    }

    private fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        launcher.launch(intent)
    }
}
package nl.avans.larsbeijaard.metar.ui

import nl.avans.larsbeijaard.metar.ui.camera.CameraViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.avans.larsbeijaard.metar.MetarApplication
import nl.avans.larsbeijaard.metar.ui.avatar.AvatarEditViewModel
import nl.avans.larsbeijaard.metar.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                avatarRepository = metarApplication().container.avatarRepository,
                downloadService = metarApplication().container.downloadService
            )
        }

        initializer {
            AvatarEditViewModel(
                savedStateHandle =  this.createSavedStateHandle(),
                avatarRepository = metarApplication().container.avatarRepository
            )
        }

        initializer {
            CameraViewModel()
        }
    }
}

fun CreationExtras.metarApplication(): MetarApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MetarApplication)
package nl.avans.larsbeijaard.metar.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.avans.larsbeijaard.metar.MetarApplication
import nl.avans.larsbeijaard.metar.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(metarApplication().container.avatarRepository)
        }
    }
}

fun CreationExtras.metarApplication(): MetarApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MetarApplication)
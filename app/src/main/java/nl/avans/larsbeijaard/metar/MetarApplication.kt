package nl.avans.larsbeijaard.metar

import android.app.Application
import nl.avans.larsbeijaard.metar.data.AppContainer
import nl.avans.larsbeijaard.metar.data.DefaultAppContainer

class MetarApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
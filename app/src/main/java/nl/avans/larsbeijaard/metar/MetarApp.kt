package nl.avans.larsbeijaard.metar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import nl.avans.larsbeijaard.metar.ui.navigation.MetarNavHost

@Composable
fun MetarApp(navController: NavHostController = rememberNavController()) {
    MetarNavHost(navController = navController)
}
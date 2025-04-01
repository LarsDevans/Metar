package nl.avans.larsbeijaard.metar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nl.avans.larsbeijaard.metar.ui.home.HomeDestination
import nl.avans.larsbeijaard.metar.ui.home.HomeScreen

@Composable
fun MetarNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier,
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen()
        }
    }
}
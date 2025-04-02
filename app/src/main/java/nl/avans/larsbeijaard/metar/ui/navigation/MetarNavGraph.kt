package nl.avans.larsbeijaard.metar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nl.avans.larsbeijaard.metar.ui.avatar.AvatarEditDestination
import nl.avans.larsbeijaard.metar.ui.avatar.AvatarEditScreen
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
            HomeScreen(navigateToAvatarEdit = {
                navController.navigate("${AvatarEditDestination.route}/${it}")
            })
        }
        composable(
            route = AvatarEditDestination.routeWithArgs,
            arguments = listOf(navArgument(AvatarEditDestination.AVATAR_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            AvatarEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
import ViewModels.LocationViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import data.Screens
import screens.CountryScreen
import screens.LocationScreen


@Composable
fun App(
    navController: NavHostController = rememberNavController(),

) {
    val viewModel = LocationViewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.Start.name,
        modifier = Modifier.padding()
    ) {
        composable(route = Screens.Start.name) {
            LocationScreen(
                viewModel
            ) { name -> navController.navigate("${Screens.CountryDetails.name}/$name") }
        }
        composable(
            route = "${Screens.CountryDetails.name}/{name}",
            arguments = listOf(navArgument("name") {
                defaultValue = "no argument"
                nullable = true
            })) {
                backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "no argument"
            CountryScreen(viewModel, navController::popBackStack, name = name)
        }
    }
}


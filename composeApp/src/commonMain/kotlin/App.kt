import ViewModels.LocationViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import data.Screen
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import screens.CountryScreen
import screens.LocationScreen


@Composable
fun App(navController: NavHostController = rememberNavController()) {
    KoinContext {
//    val viewModel = LocationViewModel()
        val viewModel = koinViewModel<LocationViewModel>()

        NavHost(
            navController = navController,
            startDestination = Screen.Start.name,
            modifier = Modifier.padding()
        ) {
            composable(route = Screen.Start.name) {
                LocationScreen(viewModel) { name -> navController.navigate("${Screen.CountryDetails.name}/$name") }
            }
            composable(
                route = "${Screen.CountryDetails.name}/{name}",
                arguments = listOf(navArgument("name") {})
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: "no argument"
                CountryScreen(viewModel, navController::popBackStack, name = name)
            }
        }
    }
}

@Composable
inline fun<reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
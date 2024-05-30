import ViewModels.LocationViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            LocationScreen(viewModel, onNextClick = { navController.navigate(Screens.CountryDetails.name)})
        }
        composable(route = Screens.CountryDetails.name) {
            CountryScreen(viewModel, navController::popBackStack)
        }
    }
}


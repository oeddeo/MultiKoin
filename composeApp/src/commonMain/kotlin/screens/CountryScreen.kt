package screens

import Components.NavigationIcon
import ViewModels.LocationViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import me.sample.library.resources.Res
import me.sample.library.resources.this_is_country
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(
    viewModel: LocationViewModel = viewModel { LocationViewModel() },
    onBackClick: ()->Unit,
    onNextClick: ()->Unit = {},
    name: String,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Country") },
                navigationIcon = { NavigationIcon(onClick = { onBackClick() }) }
            )
        }
    ) {
        MaterialTheme {
            Column(modifier = Modifier.padding(it)) {
                Text(text = "${stringResource(Res.string.this_is_country)} ${uiState.userLocation.location}")

                Text(text = name)
            }
        }
    }
}
package screens

import Components.NavigationIcon
import ViewModels.LocationViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import data.Screens
import me.sample.library.resources.Res
import me.sample.library.resources.se
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(
    viewModel: LocationViewModel = viewModel { LocationViewModel() },
    onNextClick: ()-> Unit,
    )
{
    val uiState by viewModel.uiState.collectAsState()
    var showCountries by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Location") },
                navigationIcon = { }
            )
        }
    ) {
        MaterialTheme {
            Column(
                modifier = Modifier.padding(it)
            ) {
                Text(
                    text = "time in ${uiState.userLocation.location} is: ${uiState.userLocation.localTime}",
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                )

                Row(
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    DropdownMenu(
                        expanded = showCountries,
                        onDismissRequest = { showCountries = false }) {
                        viewModel.countries().forEach { (name, zone, image) ->
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.currentTime(zone)
                                    viewModel.updateLocation(name)
                                    showCountries = false
                                },
                                text = { Text(name) },
                                trailingIcon = {
                                    Image(
                                        painter = painterResource(image),
                                        contentDescription = "$name flag",
                                        modifier = Modifier.size(50.dp).padding(end = 10.dp)
                                    )
                                }

                            )
                        }
                    }
                }
                Button(modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                    onClick = { showCountries = !showCountries }) {
                    Text("Select Location")
                }

                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { onNextClick()}
                )
                { Text("Go to 2nd Screen") }
            }

            AnimatedVisibility(uiState.showContent) {
                Column(
                    Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.se), null)
                }
            }
        }
    }
}

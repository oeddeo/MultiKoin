package ViewModels

import androidx.lifecycle.ViewModel
import data.Country
import data.LocationState
import data.UserLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.IllegalTimeZoneException
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import me.sample.library.resources.Res
import me.sample.library.resources.eg
import me.sample.library.resources.fr
import me.sample.library.resources.id
import me.sample.library.resources.jp
import me.sample.library.resources.mx
import me.sample.library.resources.se

class LocationViewModel(val injectedString: String) : ViewModel() {



    private val _uisState = MutableStateFlow(LocationState(Country(), UserLocation()))
    val uiState: StateFlow<LocationState> = _uisState.asStateFlow()

    fun updateLocation(newUserLocation: String) {
        val newLocation = _uisState.value.userLocation.copy(location = newUserLocation)
        _uisState.value = _uisState.value.copy(userLocation = newLocation)
    }

    fun toggleWindowLicker() {
        _uisState.value = _uisState.value.copy(showContent = !_uisState.value.showContent)
    }

    fun currentTime(zone: TimeZone) {
        fun LocalTime.formatted() = "$hour:$minute:$second"

        try {
            val time = Clock.System.now()
            val localTime = time.toLocalDateTime(zone).time
            val newLocation = _uisState.value.userLocation.copy(localTime = localTime.formatted())
            _uisState.value = _uisState.value.copy(userLocation = newLocation)
        } catch (e: IllegalTimeZoneException) {
            val newLocation = _uisState.value.userLocation.copy(localTime = "Error")
            _uisState.value = _uisState.value.copy(userLocation = newLocation)
        }
    }

    fun countries() = listOf(
        Country("Japan", getTimeZoneOrDefault("Asia/Tokyo"), Res.drawable.jp),
        Country("France", getTimeZoneOrDefault("Europe/Paris"), Res.drawable.fr),
        Country("Mexico", getTimeZoneOrDefault("America/Mexico_City"), Res.drawable.mx),
        Country("Indonesia", getTimeZoneOrDefault("Asia/Jakarta"), Res.drawable.id),
        Country("Egypt", getTimeZoneOrDefault("Africa/Cairo"), Res.drawable.eg),
        Country("Sweden", getTimeZoneOrDefault("Europe/Stockholm"), Res.drawable.se)
    )

    private fun getTimeZoneOrDefault(zoneId: String): TimeZone {
        return try {
            TimeZone.of(zoneId)
        } catch (e: IllegalTimeZoneException) {
            TimeZone.UTC
        }
    }
}

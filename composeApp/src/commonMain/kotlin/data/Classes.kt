package data

import kotlinx.datetime.TimeZone
import me.sample.library.resources.Res
import me.sample.library.resources.country_screen
import me.sample.library.resources.jp
import me.sample.library.resources.start_screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


data class Country(
    val name: String = "",
    val zone: TimeZone = TimeZone.UTC,
    val flag: DrawableResource = Res.drawable.jp)


data class UserLocation(
    val location: String = "Europe/Paris",
    val localTime: String = "current time",
    val showContent: Boolean = false)


enum class Screen(val title: StringResource) {
    Start(title = Res.string.start_screen),
    CountryDetails(title = Res.string.country_screen)
}


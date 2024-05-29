package data

data class LocationState(
    val country: Country = Country(),
    val userLocation: UserLocation,
    val showContent: Boolean = false)


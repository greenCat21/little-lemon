package ro.pyo.littlelemon

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route = "Home"
}

object Profile : Destinations {
    override val route = "Profile"
}

object Onboard : Destinations {
    override val route = "Onboard"
}
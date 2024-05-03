package ru.bulkapedia.presentation.screens

sealed class Screen(
    val route: String
) {

    data object WIKI : Screen(ROUTE_WIKI)
    data object TOURNAMENTS : Screen(ROUTE_TOURNAMENTS)
    data object AUTH : Screen(ROUTE_AUTH)
    data object PROFILE : Screen(ROUTE_PROFILE)

    private companion object {
        const val ROUTE_WIKI = "wiki"
        const val ROUTE_TOURNAMENTS = "tournaments"
        const val ROUTE_AUTH = "auth"
        const val ROUTE_PROFILE = "profile"
    }

}
package ru.bulkapedia.presentation.ui.navigation

sealed class Screen(val route: String) {

    data object Wiki : Screen(ROUTE_WIKI)
    data object Categories : Screen(ROUTE_CATEGORIES)
    data object Maps : Screen(ROUTE_MAPS)
    data object Auth : Screen(ROUTE_AUTH)
    data object Login : Screen(ROUTE_LOGIN)
    data object Registration : Screen(ROUTE_REGISTRATION)

    data object Profile : Screen(ROUTE_PROFILE)
    data object Sets : Screen(ROUTE_SETS)

    data class Map(val id: String): Screen(ROUTE_MAP.replace(ARG_ID, id))

    companion object {
        private const val ARG_ID = "{id}"

        private const val ROUTE_WIKI = "wiki"
        private const val ROUTE_CATEGORIES = "categories"
        private const val ROUTE_MAPS = "maps"
        private const val ROUTE_MAP = "$ROUTE_MAPS/$ARG_ID"
        private const val ROUTE_LOGIN = "login"
        private const val ROUTE_AUTH = "auth"
        private const val ROUTE_REGISTRATION = "registration"
        private const val ROUTE_PROFILE = "profile"
        private const val ROUTE_SETS = "sets"
    }

}
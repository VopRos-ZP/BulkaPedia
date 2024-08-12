package ru.bulkapedia.presentation.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen(ROUTE_SPLASH)
    data object Root : Screen(ROUTE_ROOT)

    data object CategoriesFlow : Screen(ROUTE_CATEGORIES_FLOW)
    data object Categories : Screen(ROUTE_CATEGORIES)
    data class CategoryOf(private val r: String) : Screen(r)


    data object Notifications : Screen(ROUTE_NOTIFICATIONS)

    data object ProfileFlow : Screen(ROUTE_PROFILE_FLOW)
    data object Profile : Screen(ROUTE_PROFILE)

    data object LoginFlow : Screen(ROUTE_LOGIN_FLOW)
    data object Login : Screen(ROUTE_LOGIN)
    data object Registration : Screen(ROUTE_REGISTRATION)
    data object ForgotPassword : Screen(ROUTE_FORGOT_PASSWORD)

    companion object {

        private const val ROUTE_SPLASH = "splash"
        private const val ROUTE_ROOT = "root"

        private const val ROUTE_CATEGORIES_FLOW = "categories_flow"
        private const val ROUTE_CATEGORIES = "categories"
        private const val ROUTE_NOTIFICATIONS = "notifications"

        private const val ROUTE_PROFILE_FLOW = "profile_flow"
        private const val ROUTE_PROFILE = "profile"

        private const val ROUTE_LOGIN_FLOW = "login_flow"
        private const val ROUTE_LOGIN = "login"
        private const val ROUTE_REGISTRATION = "registration"
        private const val ROUTE_FORGOT_PASSWORD = "forgot_password"

    }

}
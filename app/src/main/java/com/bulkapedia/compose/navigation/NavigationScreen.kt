package com.bulkapedia.compose.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.bulkapedia.compose.screens.comments.CommentsScreen
import com.bulkapedia.compose.screens.createset.CreateSetScreen
import com.bulkapedia.compose.screens.dashboard.DashboardScreen
import com.bulkapedia.compose.screens.dashboard.screens.categorymanage.CategoryManageScreen
import com.bulkapedia.compose.screens.dashboard.tabs.UsersSetsScreen
import com.bulkapedia.compose.screens.devchat.DevChat
import com.bulkapedia.compose.screens.hero.HeroSets
import com.bulkapedia.compose.screens.heroes.Heroes
import com.bulkapedia.compose.screens.heroinfo.HeroInfoScreen
import com.bulkapedia.compose.screens.heroinfo.HeroesInfoScreen
import com.bulkapedia.compose.screens.information.InfoScreen
import com.bulkapedia.compose.screens.login.Login
import com.bulkapedia.compose.screens.map.SelectedMapScreen
import com.bulkapedia.compose.screens.maps.Maps
import com.bulkapedia.compose.screens.mechanics.item.MechanicScreen
import com.bulkapedia.compose.screens.mechanics.MechanicsScreen
import com.bulkapedia.compose.screens.passwordreset.PasswordResetScreen
import com.bulkapedia.compose.screens.profile.Profile
import com.bulkapedia.compose.screens.profile.VisitProfileScreen
import com.bulkapedia.compose.screens.registration.RegistrationScreen
import com.bulkapedia.compose.screens.settings.SettingsScreen
import com.bulkapedia.compose.screens.top.TopScreen

data class NavigationScreen(
    val destination: String,
    val args: List<NamedNavArgument> = emptyList(),
    val onNavigate: @Composable (Bundle?) -> Unit
)

fun navArg(name: String, type: NavType<*>): NamedNavArgument = navArgument(name) {
    this.type = type
}

fun navArg(name: String, type: NavType<*>, defaultValue: Any): NamedNavArgument = navArgument(name) {
    this.type = type
    this.defaultValue = defaultValue
}

// Navigation screens
val ToDASHBOARD = NavigationScreen(Destinations.DASHBOARD) { DashboardScreen() }
val ToHEROES = NavigationScreen(Destinations.HEROES) { Heroes(hiltViewModel()) }
val ToSETTINGS = NavigationScreen(Destinations.SETTINGS) { SettingsScreen(hiltViewModel()) }
val ToSIGN_IN = NavigationScreen(Destinations.SING_IN) { Login(hiltViewModel()) }
val ToSIGN_UP = NavigationScreen(Destinations.SING_UP) { RegistrationScreen(hiltViewModel()) }
val ToFORGOT_PASSWORD = NavigationScreen(Destinations.FORGOT_PASSWORD) { PasswordResetScreen(hiltViewModel()) }
val ToMAPS = NavigationScreen(Destinations.MAPS) { Maps(hiltViewModel()) }
val ToINFO = NavigationScreen(Destinations.INFO) { InfoScreen(hiltViewModel()) }
val ToHEROES_INFO = NavigationScreen(Destinations.HERO_INFO) { HeroesInfoScreen(hiltViewModel()) }
val ToMECHANICS = NavigationScreen(Destinations.MECHANICS) { MechanicsScreen(hiltViewModel()) }
val ToUSERS_SETS = NavigationScreen(Destinations.USERS_SETS) { UsersSetsScreen(hiltViewModel()) }
val ToCATEGORY_MANAGE = NavigationScreen(Destinations.CATEGORY_MANAGE) { CategoryManageScreen(hiltViewModel()) }
val ToMANAGE_HEROES_INFO = NavigationScreen("${Destinations.DASHBOARD}/${Destinations.HERO_INFO}") {
    HeroesInfoScreen(hiltViewModel())
}
val ToMECHANIC = NavigationScreen("${Destinations.MECHANICS}/{id}",
    listOf(navArg("id", NavType.StringType))) { args ->
    MechanicScreen(args?.getString("id")!!, hiltViewModel())
}

val ToHERO_INFO = NavigationScreen("${Destinations.HERO_INFO}/{hero}",
    listOf(navArg("hero", NavType.StringType))) { args ->
    HeroInfoScreen(args?.getString("hero")!!, hiltViewModel())
}

val ToMAP = NavigationScreen("${Destinations.MAPS}/{mapImage}",
    listOf(navArg("mapImage", NavType.StringType))) { args ->
    SelectedMapScreen(args?.getString("mapImage")!!, hiltViewModel())
}

val ToDEV_CHAT = NavigationScreen("${Destinations.DEV_CHAT}/{author}/{receiver}",
    listOf(
        navArg("author", NavType.StringType),
        navArg("receiver", NavType.StringType)
    )) { args ->
    DevChat(args?.getString("author")!!, args.getString("receiver")!!, hiltViewModel())
}

val ToSET = NavigationScreen("${Destinations.SETS}/{heroId}",
    listOf(navArg("heroId", NavType.StringType))) { args ->
    HeroSets(args?.getString("heroId")!!, hiltViewModel())
}

val ToTOP = NavigationScreen("${Destinations.TOP}/{hero}",
    listOf(navArg("hero", NavType.StringType))) { args ->
    TopScreen(args?.getString("hero")!!, hiltViewModel())
}

val ToCOMMENTS = NavigationScreen("${Destinations.COMMENTS}/{set}",
    listOf(navArg("set", NavType.StringType))) { args ->
    CommentsScreen(args?.getString("set")!!, hiltViewModel())
}

val ToVISIT = NavigationScreen("${Destinations.VISIT_PROFILE}/{nickname}",
    listOf(navArg("nickname", NavType.StringType))) { args ->
    VisitProfileScreen(args?.getString("nickname")!!)
}

val ToPROFILE = NavigationScreen("${Destinations.PROFILE}/{email}",
    listOf(navArg("email", NavType.StringType))) { args ->
    Profile(args?.getString("email")!!)
}

val ToCREATE_SET = NavigationScreen("${Destinations.CREATE_SET}/{hero}/{nickname}?setId={setId}",
    listOf(
        navArg("hero", NavType.StringType),
        navArg("nickname", NavType.StringType),
        navArg("setId", NavType.StringType, "")
    )) { args ->
    val hero = args?.getString("hero")!!
    val nickname = args.getString("nickname")!!
    val setId = args.getString("setId")!!
    CreateSetScreen(nickname, hero, setId, hiltViewModel())
}
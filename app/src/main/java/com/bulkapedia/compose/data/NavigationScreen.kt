package com.bulkapedia.compose.data

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.comments.CommentsScreen
import com.bulkapedia.compose.screens.createset.CreateSetScreen
import com.bulkapedia.compose.screens.dashboard.DashboardScreen
import com.bulkapedia.compose.screens.dashboard.screens.categorymanage.CategoryManageScreen
import com.bulkapedia.compose.screens.dashboard.tabs.UsersSetsScreen
import com.bulkapedia.compose.screens.devchat.DevChat
import com.bulkapedia.compose.screens.hero.HeroSets
import com.bulkapedia.compose.screens.heroes.HeroViewModel
import com.bulkapedia.compose.screens.heroes.Heroes
import com.bulkapedia.compose.screens.heroinfo.HeroInfoScreen
import com.bulkapedia.compose.screens.heroinfo.HeroesInfoScreen
import com.bulkapedia.compose.screens.information.InfoScreen
import com.bulkapedia.compose.screens.login.Login
import com.bulkapedia.compose.screens.maps.MapViewModel
import com.bulkapedia.compose.screens.maps.Maps
import com.bulkapedia.compose.screens.maps.SelectedMap
import com.bulkapedia.compose.screens.mechanics.MechanicScreen
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
    val onNavigate: @Composable (ToolbarCtx, Bundle?) -> Unit
)

fun navArg(name: String, type: NavType<*>): NamedNavArgument = navArgument(name) {
    this.type = type
}

fun navArg(name: String, type: NavType<*>, defaultValue: Any): NamedNavArgument = navArgument(name) {
    this.type = type
    this.defaultValue = defaultValue
}

// Navigation screens

val ToDASHBOARD = NavigationScreen(Destinations.DASHBOARD) { ctx, _ -> DashboardScreen(ctx) }
val ToHEROES = NavigationScreen(Destinations.HEROES) { ctx, _ -> Heroes(ctx) }
val ToSETTINGS = NavigationScreen(Destinations.SETTINGS) { ctx, _ -> SettingsScreen(ctx, hiltViewModel()) }
val ToSIGN_IN = NavigationScreen(Destinations.SING_IN) { ctx, _ -> Login(ctx, hiltViewModel()) }
val ToSIGN_UP = NavigationScreen(Destinations.SING_UP) { ctx, _ -> RegistrationScreen(ctx, hiltViewModel()) }
val ToFORGOT_PASSWORD = NavigationScreen(Destinations.FORGOT_PASSWORD) { ctx, _ -> PasswordResetScreen(ctx, hiltViewModel()) }
val ToMAPS = NavigationScreen(Destinations.MAPS) { ctx, _ -> Maps(ctx) }
val ToINFO = NavigationScreen(Destinations.INFO) { ctx, _ -> InfoScreen(ctx, hiltViewModel()) }
val ToHEROES_INFO = NavigationScreen(Destinations.HERO_INFO) { ctx, _ -> HeroesInfoScreen(ctx, hiltViewModel()) }
val ToMECHANICS = NavigationScreen(Destinations.MECHANICS) { ctx, _ -> MechanicsScreen(ctx, hiltViewModel()) }
val ToUSERS_SETS = NavigationScreen(Destinations.USERS_SETS) { ctx, _ -> UsersSetsScreen(ctx, hiltViewModel()) }
val ToCATEGORY_MANAGE = NavigationScreen(Destinations.CATEGORY_MANAGE) { ctx, _ -> CategoryManageScreen(ctx, hiltViewModel()) }

val ToMECHANIC = NavigationScreen("${Destinations.MECHANICS}/{id}", listOf(
    navArg("id", NavType.StringType)
)) { ctx, args ->
    MechanicScreen(ctx, args?.getString("id") ?: "", hiltViewModel())
}

val ToHERO_INFO = NavigationScreen("${Destinations.HERO_INFO}/{hero}", listOf(
    navArg("hero", NavType.StringType)
)) { ctx, args ->
    HeroInfoScreen(ctx, args?.getString("hero")!!, hiltViewModel())
}

val ToMAP = NavigationScreen("${Destinations.MAPS}/{mapImage}", listOf(
    navArg("mapImage", NavType.StringType)
)) { ctx, args ->
    val viewModel = hiltViewModel<MapViewModel>().apply {
        this.mapImage = args?.getString("mapImage")!!
    }
    SelectedMap(ctx, viewModel)
}

val ToDEV_CHAT = NavigationScreen("${Destinations.DEV_CHAT}/{author}/{receiver}",
    listOf(navArg("author", NavType.StringType),
        navArg("receiver", NavType.StringType)
    )
) { ctx, args ->
    DevChat(ctx = ctx, args?.getString("author")!!, args.getString("receiver")!!, hiltViewModel())
}

val ToSET = NavigationScreen("${Destinations.SETS}/{heroId}",
    listOf(navArg("heroId", NavType.StringType))) { ctx, args ->
    val heroId = args?.getString("heroId") ?: ""
    val viewModel = hiltViewModel<HeroViewModel>().apply { this.heroId = heroId }
    HeroSets(ctx, viewModel)
}

val ToTOP = NavigationScreen("${Destinations.TOP}/{hero}",
    listOf(navArg("hero", NavType.StringType))) { ctx, args ->
    val hero = args?.getString("hero") ?: ""
    TopScreen(ctx, hero, hiltViewModel())
}

val ToCOMMENTS = NavigationScreen("${Destinations.COMMENTS}/{set}",
    listOf(navArg("set", NavType.StringType))) { ctx, args ->
    val set: String = args?.getString("set") ?: ""
    CommentsScreen(ctx, set, hiltViewModel(), hiltViewModel())
}

val ToVISIT = NavigationScreen("${Destinations.VISIT_PROFILE}/{nickname}",
    listOf(navArg("nickname", NavType.StringType))) { ctx, args ->
    val nickname = args?.getString("nickname") ?: ""
    VisitProfileScreen(ctx, nickname, hiltViewModel())
}

val ToPROFILE = NavigationScreen(
    "${Destinations.PROFILE}/{email}",
    listOf(navArg("email", NavType.StringType))) { ctx, args ->
    val email = args?.getString("email") ?: ""
    Profile(ctx, email, hiltViewModel())
}

val ToCREATE_SET = NavigationScreen("${Destinations.CREATE_SET}/{hero}/{nickname}?setId={setId}",
    listOf(
        navArg("hero", NavType.StringType),
        navArg("nickname", NavType.StringType),
        navArg("setId", NavType.StringType, "")
    )) { ctx, args ->
    val hero = args?.getString("hero") ?: ""
    val nickname = args?.getString("nickname") ?: ""
    val setId = args?.getString("setId") ?: ""
    CreateSetScreen(ctx, nickname, hero, setId, hiltViewModel())
}
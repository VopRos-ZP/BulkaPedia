package vopros.bulkapedia.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import vopros.bulkapedia.ui.components.topbar.TopBar
import vopros.bulkapedia.ui.screens.NavGraphs
import vopros.bulkapedia.ui.screens.categories.CategoriesViewModel
import vopros.bulkapedia.ui.screens.comments.CommentsViewModel
import vopros.bulkapedia.ui.screens.createSet.CreateSetViewModel
import vopros.bulkapedia.ui.screens.destinations.CategoriesScreenDestination
import vopros.bulkapedia.ui.screens.destinations.CommentsScreenDestination
import vopros.bulkapedia.ui.screens.destinations.CreateSetScreenDestination
import vopros.bulkapedia.ui.screens.destinations.HeroInfoScreenDestination
import vopros.bulkapedia.ui.screens.destinations.HeroScreenDestination
import vopros.bulkapedia.ui.screens.destinations.HeroesScreenDestination
import vopros.bulkapedia.ui.screens.destinations.LoginScreenDestination
import vopros.bulkapedia.ui.screens.destinations.MapScreenDestination
import vopros.bulkapedia.ui.screens.destinations.MapsScreenDestination
import vopros.bulkapedia.ui.screens.destinations.ProfileControllerScreenDestination
import vopros.bulkapedia.ui.screens.destinations.ProfileScreenDestination
import vopros.bulkapedia.ui.screens.destinations.RegistrationScreenDestination
import vopros.bulkapedia.ui.screens.destinations.SettingsScreenDestination
import vopros.bulkapedia.ui.screens.hero.HeroViewModel
import vopros.bulkapedia.ui.screens.heroInfo.HeroInfoViewModel
import vopros.bulkapedia.ui.screens.heroes.HeroesViewModel
import vopros.bulkapedia.ui.screens.login.LoginViewModel
import vopros.bulkapedia.ui.screens.map.MapViewModel
import vopros.bulkapedia.ui.screens.maps.MapsViewModel
import vopros.bulkapedia.ui.screens.profile.ProfileViewModel
import vopros.bulkapedia.ui.screens.profileController.ProfileControllerViewModel
import vopros.bulkapedia.ui.screens.registration.RegistrationViewModel
import vopros.bulkapedia.ui.screens.settings.SettingsViewModel
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.ui.theme.LocalTopBarViewModel

@Composable
fun Home() {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalTopBarViewModel provides hiltViewModel(),
        LocalNavController provides navController
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar() },
            bottomBar = { BottomNavigation(navController) },
        ) {
            DestinationsNavHost(
                navGraph = NavGraphs.root,
                modifier = Modifier.padding(it),
                navController = navController,
                dependenciesContainerBuilder = {
                    /* Root */
                    dependency(CategoriesScreenDestination) { hiltViewModel<CategoriesViewModel>() }
                    dependency(ProfileControllerScreenDestination) { hiltViewModel<ProfileControllerViewModel>() }
                    /* Heroes */
                    dependency(HeroesScreenDestination) { hiltViewModel<HeroesViewModel>() }
                    dependency(HeroScreenDestination) { hiltViewModel<HeroViewModel>() }
                    dependency(HeroInfoScreenDestination) { hiltViewModel<HeroInfoViewModel>() }
                    /* Comments */
                    dependency(CommentsScreenDestination) { hiltViewModel<CommentsViewModel>() }
                    /* Create user set */
                    dependency(CreateSetScreenDestination) { hiltViewModel<CreateSetViewModel>() }
                    /* Maps */
                    dependency(MapsScreenDestination) { hiltViewModel<MapsViewModel>() }
                    dependency(MapScreenDestination) { hiltViewModel<MapViewModel>() }
                    /* Profile */
                    dependency(ProfileScreenDestination) { hiltViewModel<ProfileViewModel>() }
                    /* Auth */
                    dependency(LoginScreenDestination) { hiltViewModel<LoginViewModel>() }
                    dependency(RegistrationScreenDestination) { hiltViewModel<RegistrationViewModel>() }
                    /* Settings */
                    dependency(SettingsScreenDestination) { hiltViewModel<SettingsViewModel>() }
                }
            )
        }
    }
}
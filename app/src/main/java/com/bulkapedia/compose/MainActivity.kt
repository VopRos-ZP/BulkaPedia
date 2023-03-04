@file:Suppress("FunctionName")
package com.bulkapedia.compose

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bulkapedia.compose.elements.Toolbar
import com.bulkapedia.compose.navigation.*
import com.bulkapedia.compose.screens.Screen
import com.bulkapedia.compose.screens.heroes.HeroesNavList
import com.bulkapedia.compose.screens.login.LoginNav
import com.bulkapedia.compose.screens.profile.ProfileNav
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.CTX
import com.bulkapedia.compose.data.gears.GearsList
import com.bulkapedia.compose.elements.ScreenWithInfo
import com.bulkapedia.compose.screens.information.InfoListNav
import com.bulkapedia.compose.util.GEARS_RES
import com.bulkapedia.compose.util.HEROES_RES
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appUpdateManager: AppUpdateManager
    private val updateAvailable = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val showUpdate = mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CTX = this
        GEARS_LIST = GearsList()
        ICON_LIST = (HEROES_RES + GEARS_RES).toMutableMap()
        appUpdateManager = AppUpdateManagerFactory.create(this)
        appUpdateManager.registerListener { state ->
            showUpdate.value = state.installStatus() == InstallStatus.DOWNLOADED
        }
        checkUpdate()
        setContent {
            val nc = rememberNavController()
            val ctx = remember { mutableStateOf(
                ToolbarCtx(MutableLiveData(
                    ToolbarData(title = "", showBackButton = false,  isVisibleSettings = true)), nc)
            ) }
            MainTheme(ctx.value) {
                Surface(color = LocalBulkaPediaColors.current.primary) {
                    ScreenWithInfo { action ->
                        NavHost(navController = nc, startDestination = "splash") {
                            composable("splash") { SplashScreen(nc) }
                            composable("main") { MainScreen(ctx.value) }
                        }
                        if (showUpdate.value) {
                            action.showInfo("Обновление загружено, нажмите ок, чтобы перезапустить приложение") {
                                appUpdateManager.completeUpdate()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && info.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                updateAvailable.value = true
                startUpdate(info)
            } else {
                updateAvailable.value = false
            }
        }
    }

    private fun startUpdate(info: AppUpdateInfo) {
        appUpdateManager.startUpdateFlowForResult(info, AppUpdateType.FLEXIBLE, this, 1101)
    }

}

@Composable
fun SplashScreen(nc: NavController) {
    LaunchedEffect(key1 = Unit, block = { nc.navigate(route = "main") })
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    toolbarCtx: ToolbarCtx
) {
    val store = DataStore(LocalContext.current)
    val sign by store.getSign.collectAsState(initial = false)
    val email by store.getEmail.collectAsState(initial = "")

    val childNC = rememberNavController()
    val bottomItems = if (sign == true && email != null && email?.isNotEmpty() == true)
        listOf(Screen.Heroes, Screen.Wiki, Screen.Profile(email ?: ""))
    else
        listOf(Screen.Heroes, Screen.Wiki, Screen.SignIn)

    toolbarCtx.navController = childNC
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Primary,
        topBar = { Toolbar(toolbarCtx) },
        bottomBar = { BottomNavigation(
            backgroundColor = PrimaryDark,
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryDark)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        ) {
            val navBackStackEntry by childNC.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val previousDestination = remember { mutableStateOf(bottomItems.first().route) }

            bottomItems.map { tab ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                    label = { Text(CTX.getString(tab.title), color = Teal200) },
                    icon = { Image(tab.icon, contentDescription = "",
                        colorFilter = ColorFilter.tint(Teal200)) },
                    onClick = {
                        if (tab.route == previousDestination.value) return@BottomNavigationItem
                        previousDestination.value = tab.route

                        childNC.navigate(tab.route) {
                            popUpTo(childNC.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        } }
    ) {
        NavHost(navController = childNC, startDestination = Screen.Heroes.route) {
            composable(Screen.Heroes.route) { HeroesNavList(toolbarCtx) }
            composable(Screen.Wiki.route) { InfoListNav(toolbarCtx) }
            if (sign == true && email != null && email?.isNotEmpty() == true) {
                composable("${Destinations.PROFILE}/$email") {
                    ProfileNav(toolbarCtx, "${Destinations.PROFILE}/$email", email!!)
                }
            } else {
                composable(Screen.SignIn.route) { LoginNav(toolbarCtx) }
            }
        }
    }
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

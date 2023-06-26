package com.bulkapedia.compose

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bulkapedia.compose.data.gears.GearsList
import com.bulkapedia.compose.elements.ScreenWithInfo
import com.bulkapedia.compose.screens.Home
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.util.CTX
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
    private val updateAvailable = MutableLiveData(false)
    private val showUpdate = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CTX = this
        GEARS_LIST = GearsList()
        ICON_LIST = (HEROES_RES + GEARS_RES).toMutableMap()

        /* Check update */
        appUpdateManager = AppUpdateManagerFactory.create(this)
        appUpdateManager.registerListener { state ->
            showUpdate.value = state.installStatus() == InstallStatus.DOWNLOADED
        }
        checkUpdate()
        /* set UI */
        setContent {
            val nc = rememberNavController()
            CompositionLocalProvider(
                LocalNavController provides nc
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(PrimaryDark)
                ) {
                    ScreenWithInfo { info ->
                        if (showUpdate.value) {
                            info.showInfo("Обновление загружено, нажмите `ОК`, чтобы перезапустить приложение") {
                                appUpdateManager.completeUpdate()
                            }
                        }
                        NavHost(navController = nc, startDestination = "splash") {
                            composable("splash") { SplashScreen() }
                            composable("home") { Home() }
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
fun SplashScreen() {
    val nc = LocalNavController.current
    LaunchedEffect(Unit) { nc.navigate(route = "home") }
}

@Composable
fun LockPortrait() { LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) }

@Composable
fun LockLandscape() { LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) }

@Composable
fun LockScreenOrientation(orientation: Int) {
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = orientation
}

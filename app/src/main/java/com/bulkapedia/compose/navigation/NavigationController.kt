@file:Suppress("FunctionName")
package com.bulkapedia.compose.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bulkapedia.compose.data.NavigationScreen

@Composable
fun Navigation(
    startDestination: String,
    toolbarCtx: ToolbarCtx,
    screens: List<NavigationScreen> = emptyList()
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        screens.forEach { screen ->
            composable(screen.destination, arguments = screen.args) {
                screen.onNavigate.invoke(toolbarCtx.apply { this.navController = navController }, it.arguments)
            }
        }
    }
}

fun NavController.navigate(route: String, params: Bundle = bundleOf(), builder: NavOptionsBuilder.() -> Unit = {}) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    navigate(route, builder)
}

fun NavController.navigate(route: String, param: Pair<String, Parcelable>?, builder: NavOptionsBuilder.() -> Unit = {}) {
    param?.let { this.currentBackStackEntry?.arguments?.putParcelable(param.first, param.second)  }

    navigate(route, builder)
}

fun NavController.navigate(route: String, params: List<Pair<String, Parcelable>>?, builder: NavOptionsBuilder.() -> Unit = {}) {
    params?.let {
        val arguments = this.currentBackStackEntry?.arguments
        params.forEach { arguments?.putParcelable(it.first, it.second) }
    }

    navigate(route, builder)
}

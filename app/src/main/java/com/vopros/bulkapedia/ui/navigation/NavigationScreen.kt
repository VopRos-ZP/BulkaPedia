package com.vopros.bulkapedia.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

data class NavigationScreen(
    val destination: String,
    val args: List<NamedNavArgument> = emptyList(),
    val onNavigate: @Composable (Bundle?) -> Unit
)

fun navArg(name: String, type: NavType<*>): NamedNavArgument = navArgument(name) {
    this.type = type
}

fun <T> navArg(name: String, type: NavType<T>, defaultValue: T): NamedNavArgument = navArgument(name) {
    this.type = type
    this.defaultValue = defaultValue
    this.nullable = type.isNullableAllowed
}
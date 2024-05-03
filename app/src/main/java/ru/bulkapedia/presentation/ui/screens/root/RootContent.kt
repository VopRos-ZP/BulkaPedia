package ru.bulkapedia.presentation.ui.screens.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.stack.backStack
import com.arkivanov.decompose.router.stack.items
import ru.bulkapedia.presentation.ui.screens.categories.CategoriesScreen
import ru.bulkapedia.presentation.ui.screens.login.LoginScreen

@Composable
fun RootContent(component: RootComponent) {
    Scaffold(
        topBar = {},
        bottomBar = { BottomNavigationBar(component = component) }
    ) {
        Children(
            stack = component.stack,
            modifier = Modifier.padding(it)
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.Categories -> CategoriesScreen(component = instance.component)
                is RootComponent.Child.Login -> LoginScreen(component = instance.component)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(component: RootComponent) {
    NavigationBar {
        component.stack.items.forEach {
            NavigationBarItem(
                selected = component.stack.backStack.last() == it,
                onClick = {  },
                icon = { /*Icon(imageVector = icon, contentDescription = null)*/ }
            )
        }
    }
}
package ru.bulkapedia.presentation.ui.screens.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.backStack
import ru.bulkapedia.presentation.ui.navigation.AppNavGraph
import ru.bulkapedia.presentation.ui.navigation.BottomNavItem

@Composable
fun RootContent(component: RootComponent) {
    val stack by component.stack.subscribeAsState()


}


//Scaffold(
//        bottomBar = {
//            NavigationBar {
//                BottomNavItem.entries.forEach { item ->
//                    val selected = stack.backStack.any { it.configuration ==  }
//                    NavigationBarItem(
//                        selected = selected,
//                        onClick = {
//                            if (!selected) {
//
//                            }
//                        },
//                        icon = { Icon(imageVector = item.image, contentDescription = null) }
//                    )
//                }
//            }
//        }
//    ) { inner ->
//        Children(
//            modifier = Modifier.padding(inner),
//            stack = component.stack,
//            animation = stackAnimation(slide()),
//        ) {
//
//        }
//    }
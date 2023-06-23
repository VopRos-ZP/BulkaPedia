@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bulkapedia.compose.data.repos.database.User
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun ScreenWithTagDialog(
    content: @Composable (ScreenAction.AddTagAction) -> Unit
) {
    // if change main data
    val userState = remember { mutableStateOf<User?>(null) }
    val show = remember { mutableStateOf(false) }
    val defHero = remember { mutableStateOf("Арни") }
    val defKills = remember { mutableStateOf("10000") }
    val defWR = remember { mutableStateOf("50") }
    val defRevives = remember { mutableStateOf("0") }
    val action = ScreenAction.AddTagAction(userState, show, defHero, defKills, defWR, defRevives)
    // UI
    CenteredBox {
        content.invoke(action)
        AnimatedVisibility(show.value) {
            MainTagDialog(action) { h, s ->

            }
        }
    }
}
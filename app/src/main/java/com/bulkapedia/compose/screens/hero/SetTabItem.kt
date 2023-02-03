package com.bulkapedia.compose.screens.hero

sealed class SetTabItem (val title: String) {
    object Number1: SetTabItem("№1")
    object Number2: SetTabItem("№2")
    object Number3: SetTabItem("№3")
}

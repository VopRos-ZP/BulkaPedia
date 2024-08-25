package ru.bulkapedia.presentation.screens.root

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.router.stack.ChildStack

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {

    }

}
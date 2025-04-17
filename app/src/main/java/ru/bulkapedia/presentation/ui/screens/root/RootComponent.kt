package ru.bulkapedia.presentation.ui.screens.root

import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import ru.bulkapedia.presentation.components.LoginComponent
import ru.bulkapedia.presentation.ui.screens.categories.component.CategoriesComponent

interface RootComponent {

    val stack: Value<ChildPages<*, Child>>

    sealed interface Child {
        data class Categories(val component: CategoriesComponent) : Child
        data class Login(val component: LoginComponent) : Child
    }

    fun selectPage(index: Int)

}
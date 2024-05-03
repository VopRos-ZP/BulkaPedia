package ru.bulkapedia.presentation.def

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import kotlinx.parcelize.Parcelize
import ru.bulkapedia.presentation.components.RootComponent

class DefaultRootComponent(
    context: ComponentContext
) : RootComponent, ComponentContext by context {

    sealed interface Config : Parcelable {
        @Parcelize
        data object Wiki : Config
    }

    private val navigation = StackNavigation<Config>()



}
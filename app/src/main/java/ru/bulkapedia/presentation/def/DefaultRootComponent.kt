package ru.bulkapedia.presentation.def

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.parcelize.Parcelize
import ru.bulkapedia.presentation.components.RootComponent
import ru.bulkapedia.presentation.wiki.impl.DefaultWikiComponent

class DefaultRootComponent(
    context: ComponentContext
) : RootComponent, ComponentContext by context {

    sealed interface Config : Parcelable {
        @Parcelize
        data object Wiki : Config
    }

    private val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<Config, ComponentContext>> = childStack(
        source = navigation,
        initialConfiguration = Config.Wiki,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        context: ComponentContext,
    ): ComponentContext {
        return when (config) {
            Config.Wiki -> DefaultWikiComponent(context) {}
        }
    }

}
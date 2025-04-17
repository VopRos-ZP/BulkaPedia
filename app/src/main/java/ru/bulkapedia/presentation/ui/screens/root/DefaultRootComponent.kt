package ru.bulkapedia.presentation.ui.screens.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class DefaultRootComponent(
    context: ComponentContext
) : RootComponent, KoinComponent, ComponentContext by context {

    private val navigation = PagesNavigation<Config>()

    override val stack: Value<ChildPages<*, RootComponent.Child>> = childPages(
        source = navigation,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        context: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.Categories -> RootComponent.Child.Categories(
                get(parameters = {
                    parametersOf(
                        { navigation.select(1) },
                        context
                    )
                })
            )
            is Config.Login -> RootComponent.Child.Login(
                get(parameters = { parametersOf(context) })
            )
        }
    }

    override fun selectPage(index: Int) {
        navigation.select(index)
    }

    @Serializable
    sealed interface Config {

        @Serializable
        data object Categories : Config

        @Serializable
        data object Login : Config

    }

}
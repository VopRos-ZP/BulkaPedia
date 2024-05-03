package ru.bulkapedia.presentation.ui.screens.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.bulkapedia.presentation.def.DefaultLoginComponent
import ru.bulkapedia.presentation.ui.screens.categories.component.DefaultCategoriesComponent

class DefaultRootComponent @AssistedInject constructor(
    private val categoriesComponentFactory: DefaultCategoriesComponent.Factory,
    private val loginComponentFactory: DefaultLoginComponent.Factory,
    @Assisted("context") context: ComponentContext
) : RootComponent, ComponentContext by context {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Categories,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        context: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.Categories -> {
                val component = categoriesComponentFactory.create(
                    onCategoryClick = {
                        navigation.push(Config.Login)
                    },
                    context = context
                )
                RootComponent.Child.Categories(component)
            }
            is Config.Login -> {
                val component = loginComponentFactory.create(
                    context = context
                )
                RootComponent.Child.Login(component)
            }
        }
    }

    @Serializable
    sealed interface Config {

        @Serializable
        data object Categories: Config

        @Serializable
        data object Login: Config

    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("context") context: ComponentContext
        ): DefaultRootComponent

    }

}
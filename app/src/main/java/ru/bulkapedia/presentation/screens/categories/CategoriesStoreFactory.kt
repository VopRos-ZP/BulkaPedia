package ru.bulkapedia.presentation.screens.categories

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.usecase.category.GetAllCategoryUseCase
import ru.bulkapedia.presentation.screens.categories.Categories.Intent
import ru.bulkapedia.presentation.screens.categories.Categories.State
import ru.bulkapedia.presentation.screens.categories.Categories.Label
import ru.bulkapedia.presentation.screens.categories.Categories.Msg
import ru.bulkapedia.presentation.screens.categories.Categories.Action

class CategoriesStoreFactory(
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val storeFactory: StoreFactory,
) {

    fun create(): CategoriesStore =
        object : CategoriesStore,
            Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
                name = "CategoriesStore",
                initialState = State(),
                bootstrapper = coroutineBootstrapper {
                    launch {
                        getAllCategoryUseCase().collect {
                            dispatch(Action.OnCategoriesChange(it))
                        }
                    }
                },
                executorFactory = coroutineExecutorFactory {
                    onAction<Action.OnCategoriesChange> {
                        dispatch(Msg.OnCategoriesChange(it.value))
                    }
                    onIntent<Intent.OnCategoryClick> {
                        publish(Label.Navigate(it.route))
                    }
                },
                reducer = {
                    when (it) {
                        is Msg.OnCategoriesChange -> copy(categories = it.value)
                    }
                }
            ) {}

}
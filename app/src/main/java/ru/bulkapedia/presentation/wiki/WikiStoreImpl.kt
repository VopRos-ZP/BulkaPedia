package ru.bulkapedia.presentation.wiki

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.domain.repository.CategoryRepository
import javax.inject.Inject

class WikiStoreImpl(
    private val storeFactory: StoreFactory = DefaultStoreFactory(),
    private val categoryRepository: CategoryRepository
) {

    sealed interface Action  {
        data class UpdateCategories(val categories: List<Category>) : Action
    }

    sealed interface Msg {
        data class UpdateCategories(val categories: List<Category>) : Msg
    }

    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): WikiStore = object : WikiStore,
            Store<WikiStore.Intent, WikiStore.State, WikiStore.Label> by storeFactory.create<WikiStore.Intent, Action, Msg, WikiStore.State, WikiStore.Label>(
        name = "WikiStore",
        initialState = WikiStore.State(emptyList()),
        bootstrapper = coroutineBootstrapper {
            launch {
                categoryRepository.subscribe()
                categoryRepository.categories.collect {
                    dispatch(Action.UpdateCategories(it))
                }
            }
        },
        executorFactory = coroutineExecutorFactory {
            onAction<Action.UpdateCategories> {
                dispatch(Msg.UpdateCategories(it.categories))
            }
            onIntent<WikiStore.Intent.OnCategoryClick> {
                publish(WikiStore.Label.OnCategoryClicked)
            }
        },
        reducer = {
            when (it) {
                is Msg.UpdateCategories -> copy(categories = it.categories)
            }
        }
    ) {}

}
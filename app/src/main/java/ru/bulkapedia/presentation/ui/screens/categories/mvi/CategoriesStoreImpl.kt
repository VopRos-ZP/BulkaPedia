package ru.bulkapedia.presentation.ui.screens.categories.mvi

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.bulkapedia.di.qualifiers.DefaultSF
import ru.bulkapedia.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoriesStoreFactory @Inject constructor(
    @DefaultSF
    private val storeFactory: StoreFactory,
    private val categoryRepository: CategoryRepository
) {

    fun create(): CategoriesStore = object : CategoriesStore,
        Store<Categories.Intent, Categories.State, Categories.Label> by storeFactory.create<Categories.Intent, Categories.Action, Categories.Msg, Categories.State, Categories.Label>(
            name = "CategoriesStore",
            initialState = Categories.State(),
            bootstrapper = coroutineBootstrapper {
                val handler = CoroutineExceptionHandler { _, throwable ->

                    Log.e("CategoriesStore", throwable.stackTraceToString())

                    dispatch(Categories.Action.Error(throwable.localizedMessage))
                }
                launch(handler) {
                    categoryRepository.categories.collect {
                        dispatch(Categories.Action.CategoriesChanged(it))
                    }
                }
            },
            executorFactory = coroutineExecutorFactory {
                onAction<Categories.Action.CategoriesChanged> {
                    dispatch(Categories.Msg.CategoriesChanged(it.value))
                }
                onAction<Categories.Action.Error> {
                    dispatch(Categories.Msg.Error(it.value))
                }
                onIntent<Categories.Intent.CloseError> {
                    dispatch(Categories.Msg.Error(null))
                }
                onIntent<Categories.Intent.OnCategoryClick> {
                    publish(Categories.Label.NavigationCategory(it.category))
                }
            },
            reducer = {
                when (it) {
                    is Categories.Msg.CategoriesChanged -> copy(categories = it.value)
                    is Categories.Msg.Error -> copy(error = it.value)
                }
            }
        ) {}

}
package ru.bulkapedia.presentation.ui.screens.categories.mvi

import ru.bulkapedia.domain.model.Category

object Categories {

    data class State(
        val categories: List<Category> = emptyList(),
        val error: String? = null
    )

    sealed interface Intent {
        data class OnCategoryClick(val category: Category) : Intent
        data object CloseError : Intent
    }

    sealed interface Label {
        data class Snackbar(val message: String) : Label
        data class NavigationCategory(val category: Category) : Label
    }

    sealed interface Msg {
        data class CategoriesChanged(val value: List<Category>) : Msg
        data class Error(val value: String?) : Msg
    }

    sealed interface Action {
        data class CategoriesChanged(val value: List<Category>) : Action
        data class Error(val value: String?) : Action
    }

}
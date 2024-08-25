package ru.bulkapedia.presentation.screens.categories

import ru.bulkapedia.domain.model.Category

object Categories {

    data class State(
        val categories: List<Category> = emptyList()
    )

    sealed interface Intent {
        data class OnCategoryClick(val route: String) : Intent
    }

    sealed interface Label {
        data class Navigate(val route: String) : Label
    }

    sealed interface Action {
        data class OnCategoriesChange(val value: List<Category>) : Action
    }

    sealed interface Msg {
        data class OnCategoriesChange(val value: List<Category>) : Msg
    }

}
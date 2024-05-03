package ru.bulkapedia.presentation.wiki

import com.arkivanov.mvikotlin.core.store.Store
import ru.bulkapedia.domain.model.Category

interface WikiStore : Store<WikiStore.Intent, WikiStore.State, WikiStore.Label> {

    data class State(
        val categories: List<Category>
    )

    sealed interface Label {
        data object OnCategoryClicked : Label
    }

    sealed interface Intent {
        data class OnCategoryClick(val category: Category) : Intent
    }

}
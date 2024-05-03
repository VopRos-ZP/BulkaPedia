package ru.bulkapedia.presentation.ui.screens.profile.mvi

import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.model.UserSet

object Profile {

    data class State(
        val user: User,
        val yourSets: List<UserSet> = emptyList(),
        val favSets: List<UserSet> = emptyList(),
    )

    sealed interface Intent {

    }

    sealed interface Label {

    }

}
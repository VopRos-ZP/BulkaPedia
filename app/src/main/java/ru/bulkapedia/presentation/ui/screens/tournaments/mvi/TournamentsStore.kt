package ru.bulkapedia.presentation.ui.screens.tournaments.mvi

import com.arkivanov.mvikotlin.core.store.Store
import ru.bulkapedia.domain.model.Tournament

interface TournamentsStore : Store<TournamentsStore.Intent, TournamentsStore.State, TournamentsStore.Label> {

    data class State(
        val tournaments: List<Tournament> = emptyList()
    )

    sealed interface Intent {

    }

    sealed interface Label {

    }

}
package ru.bulkapedia.presentation.ui.screens.profile.mvi

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.model.UserSet
import ru.bulkapedia.domain.repository.UserRepository

class ProfileStoreFactory (

    private val storeFactory: StoreFactory,
    private val userRepository: UserRepository,
) {

    fun create(user: User): ProfileStore = object : ProfileStore,
        Store<Profile.Intent, Profile.State, Profile.Label> by storeFactory.create<Profile.Intent, Action, Msg, Profile.State, Profile.Label>(
            name = "ProfileStore",
            initialState = Profile.State(user = user),
            bootstrapper = coroutineBootstrapper {
                launch {

                }
            },
            executorFactory = coroutineExecutorFactory { },
            reducer = {
                when (it) {
                    else -> copy()
                }
            }
        ) {}

    sealed interface Action

    sealed interface Msg {
        sealed class UserChanged(val value: User): Msg
        sealed class YourSetsChanged(val value: List<UserSet>): Msg
        sealed class FavSetsChanged(val value: List<UserSet>): Msg
    }

}
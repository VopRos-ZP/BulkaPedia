package ru.bulkapedia.presentation.ui.screens.registration.mvi

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.repository.AuthRepository
import ru.bulkapedia.domain.repository.UserRepository

class RegistrationStoreFactory  (
    private val storeFactory: StoreFactory,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    fun create(): RegistrationStore = object : RegistrationStore,
        Store<Registration.Intent, Registration.State, Registration.Label> by storeFactory.create<Registration.Intent, Registration.Action, Registration.Msg, Registration.State, Registration.Label>(
            name = "RegistrationStore",
            initialState = Registration.State(),
            executorFactory = coroutineExecutorFactory {
                onIntent<Registration.Intent.ErrorChanged> { dispatch(Registration.Msg.ErrorChanged(null)) }
                onIntent<Registration.Intent.EmailChanged> { dispatch(Registration.Msg.EmailChanged(it.value)) }
                onIntent<Registration.Intent.PasswordChanged> { dispatch(Registration.Msg.PasswordChanged(it.value)) }
                onIntent<Registration.Intent.ConfirmPasswordChanged> { dispatch(Registration.Msg.ConfirmPasswordChanged(it.value)) }
                onIntent<Registration.Intent.NicknameChanged> { dispatch(Registration.Msg.NicknameChanged(it.value)) }
                onIntent<Registration.Intent.NavigationBackClick> { publish(Registration.Label.BackToLogin) }
                onIntent<Registration.Intent.ToggleShowPassword> {
                    dispatch(Registration.Msg.ShowPasswordChanged(!state().isShowPassword))
                }
                onIntent<Registration.Intent.ToggleShowConfirmPassword> {
                    dispatch(Registration.Msg.ShowPasswordChanged(!state().isShowConfirmPassword))
                }
                onIntent<Registration.Intent.RegistrationClick> {
                    val handler = CoroutineExceptionHandler { _, throwable ->
                        Log.e("RegistrationStore", throwable.stackTraceToString())

                        dispatch(Registration.Msg.ErrorChanged(throwable.localizedMessage))
                    }
                    launch(handler) {
                        authRepository.signUp(state().email, state().password)?.let { uid ->
                            userRepository.upsert(User(id = uid, nickname = state().nickname))
                        }
                    }
                }
            },
            reducer = {
                when (it) {
                    is Registration.Msg.ConfirmPasswordChanged -> copy(confirmPassword = it.value)
                    is Registration.Msg.EmailChanged -> copy(email = it.value)
                    is Registration.Msg.ErrorChanged -> copy(error = it.value)
                    is Registration.Msg.NicknameChanged -> copy(nickname = it.value)
                    is Registration.Msg.PasswordChanged -> copy(password = it.value)
                    is Registration.Msg.ShowPasswordChanged -> copy(isShowPassword = it.value)
                    is Registration.Msg.ShowConfirmPasswordChanged -> copy(isShowConfirmPassword = it.value)
                }
            }
        ) {}

}
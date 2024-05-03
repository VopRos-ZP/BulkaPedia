package ru.bulkapedia.presentation.ui.screens.login.mvi

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.bulkapedia.di.qualifiers.DefaultSF
import ru.bulkapedia.domain.repository.AuthRepository
import javax.inject.Inject

class LoginStoreFactory @Inject constructor(
    @DefaultSF
    private val storeFactory: StoreFactory,
    private val authRepository: AuthRepository
) {

    fun create(): LoginStore = object : LoginStore,
        Store<Login.Intent, Login.State, Login.Label> by storeFactory.create<Login.Intent, Login.Action, Login.Msg, Login.State, Login.Label>(
            name = "LoginStore",
            initialState = Login.State(),
            executorFactory = coroutineExecutorFactory {
                onIntent<Login.Intent.EmailChanged> { dispatch(Login.Msg.EmailChanged(it.value)) }
                onIntent<Login.Intent.PasswordChanged> { dispatch(Login.Msg.PasswordChanged(it.value)) }
                onIntent<Login.Intent.ToggleShowPassword> { dispatch(Login.Msg.ToggleShowPassword(it.value)) }
                onIntent<Login.Intent.CloseError> { dispatch(Login.Msg.ErrorChanged(null)) }
                onIntent<Login.Intent.LoginClick> {
                    val handler = CoroutineExceptionHandler { _, throwable ->
                        dispatch(Login.Msg.ErrorChanged(throwable.localizedMessage))
                    }
                    launch(handler) {
                        authRepository.signIn(state().email, state().password)
                        publish(Login.Label.Success)
                    }
                }
                onIntent<Login.Intent.RegistrationClick> { publish(Login.Label.RegistrationClick) }
                onIntent<Login.Intent.ForgotPasswordClick> { publish(Login.Label.ForgotPasswordClick) }
            },
            reducer = {
                when (it) {
                    is Login.Msg.EmailChanged -> copy(email = it.value)
                    is Login.Msg.PasswordChanged-> copy(password = it.value)
                    is Login.Msg.ToggleShowPassword -> copy(isShowPassword = it.value)
                    is Login.Msg.ErrorChanged -> copy(error = it.value)
                }
            }
        ) {}

}
package ru.bulkapedia.presentation.screens.login

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.usecase.LoginUseCase
import ru.bulkapedia.domain.usecase.user.GetUserUseCase
import ru.bulkapedia.presentation.screens.login.Login.State
import ru.bulkapedia.presentation.screens.login.Login.Intent
import ru.bulkapedia.presentation.screens.login.Login.Action
import ru.bulkapedia.presentation.screens.login.Login.Label
import ru.bulkapedia.presentation.screens.login.Login.Msg

class LoginStoreFactory(
    private val loginUseCase: LoginUseCase,
    private val storeFactory: StoreFactory
) {

    fun create(): LoginStore =
        object : LoginStore,
            Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
                name = "LoginStore",
                initialState = State(),
                executorFactory = coroutineExecutorFactory {
                    onIntent<Intent.OnEmailChange> {
                        dispatch(Msg.OnEmailChange(it.value))
                    }
                    onIntent<Intent.OnPasswordChange> {
                        dispatch(Msg.OnPasswordChange(it.value))
                    }
                    onIntent<Intent.OnErrorChange> {
                        dispatch(Msg.OnErrorChange(it.value))
                    }
                    onIntent<Intent.OnShowPasswordChange> {
                        dispatch(Msg.OnShowPasswordChange)
                    }
                    onIntent<Intent.OnRegistrationClick> {
                        publish(Label.NavToRegistration)
                    }
                    onIntent<Intent.OnForgotPasswordClick> {
                        publish(Label.NavToForgotPassword)
                    }
                    onIntent<Intent.OnLoginClick> {
                        val state = state()
                        launch {
                            if (loginUseCase(state.email, state.password)) {
                                publish(Label.NavToProfile)
                            }
                        }
                    }
                },
                reducer = {
                    when (it) {
                        is Msg.OnEmailChange -> copy(email = it.value)
                        is Msg.OnPasswordChange -> copy(password = it.value)
                        is Msg.OnErrorChange -> copy(error = it.value)
                        is Msg.OnShowPasswordChange -> copy(showPassword = !showPassword)
                    }
                }
            ) {}

}
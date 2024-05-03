package ru.bulkapedia.presentation.def

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.bulkapedia.presentation.components.LoginComponent
import ru.bulkapedia.presentation.ui.screens.login.mvi.Login
import ru.bulkapedia.presentation.ui.screens.login.mvi.LoginStoreFactory

class DefaultLoginComponent @AssistedInject constructor(
    private val storeFactory: LoginStoreFactory,
    @Assisted("context") context: ComponentContext
) : LoginComponent, ComponentContext by context {

    private val store = instanceKeeper.getStore { storeFactory.create() }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<Login.State> = store.stateFlow

    override fun onEmailChanged(email: String) {
        store.accept(Login.Intent.EmailChanged(email))
    }

    override fun onPasswordChanged(password: String) {
        store.accept(Login.Intent.PasswordChanged(password))
    }

    override fun onLoginClick() {
        store.accept(Login.Intent.LoginClick)
    }

    override fun onSignUpClick() {
        store.accept(Login.Intent.RegistrationClick)
    }

    override fun onForgetPasswordClick() {
        store.accept(Login.Intent.ForgotPasswordClick)
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("context") context: ComponentContext
        ): DefaultLoginComponent

    }

}
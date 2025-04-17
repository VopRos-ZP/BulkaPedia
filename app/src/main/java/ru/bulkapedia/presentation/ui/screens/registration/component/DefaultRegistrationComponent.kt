package ru.bulkapedia.presentation.ui.screens.registration.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.presentation.extensions.componentScope
import ru.bulkapedia.presentation.ui.screens.registration.mvi.Registration
import ru.bulkapedia.presentation.ui.screens.registration.mvi.RegistrationStoreFactory

class DefaultRegistrationComponent(
    private val registrationStoreFactory: RegistrationStoreFactory,
    private val onNavBackClick: () -> Unit,
    private val onSuccess: () -> Unit,
    context: ComponentContext
) : RegistrationComponent, ComponentContext by context {

    private val store = instanceKeeper.getStore { registrationStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    Registration.Label.BackToLogin -> onNavBackClick()
                    Registration.Label.Success -> onSuccess()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<Registration.State> = store.stateFlow

    override fun onEmailChanged(email: String) {
        store.accept(Registration.Intent.EmailChanged(email))
    }

    override fun onPasswordChanged(password: String) {
        store.accept(Registration.Intent.PasswordChanged(password))
    }

    override fun toggleShowPassword() {
        store.accept(Registration.Intent.ToggleShowPassword)
    }

    override fun onConfirmPasswordChanged(confirmPassword: String) {
        store.accept(Registration.Intent.ConfirmPasswordChanged(confirmPassword))
    }

    override fun toggleShowConfirmPassword() {
        store.accept(Registration.Intent.ToggleShowConfirmPassword)
    }

    override fun onNicknameChanged(nickname: String) {
        store.accept(Registration.Intent.NicknameChanged(nickname))
    }

    override fun onNavBackClick() {
        store.accept(Registration.Intent.NavigationBackClick)
    }

    override fun onRegistrationClick() {
        store.accept(Registration.Intent.RegistrationClick)
    }

    override fun onCloseError() {
        store.accept(Registration.Intent.ErrorChanged)
    }

}
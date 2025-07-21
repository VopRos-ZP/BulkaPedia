package ru.bulkapedia.presentation.ui.screens.registration

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container

class RegistrationVewModel(

) : ViewModel(), ContainerHost<RegistrationViewState, RegistrationSideEffect> {

    override val container = container<RegistrationViewState, RegistrationSideEffect>(
        initialState = RegistrationViewState()
    )

    fun onEmailChange(value: String) = blockingIntent {
        reduce { state.copy(email = value) }
    }

    fun onPasswordChange(value: String) = blockingIntent {
        reduce { state.copy(password = value) }
    }

    fun onNicknameChange(value: String) = blockingIntent {
        reduce { state.copy(nickname = value) }
    }

    fun onLoginClick() = intent {
        postSideEffect(RegistrationSideEffect.OnLoginClick)
    }

    fun onRegistrationClick() = intent {
        try {
            postSideEffect(RegistrationSideEffect.OnSuccessRegistration)
        } catch (e: Exception) {
            reduce { state.copy(error = e.localizedMessage, isShowError = true) }
        }
    }

    fun onTogglePasswordClick() = intent {
        reduce { state.copy(isShowPassword = !state.isShowPassword) }
    }

    fun onCloseErrorClick() = intent {
        reduce { state.copy(isShowError = false) }
    }

}
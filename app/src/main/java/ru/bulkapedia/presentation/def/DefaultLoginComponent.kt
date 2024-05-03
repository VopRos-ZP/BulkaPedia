package ru.bulkapedia.presentation.def

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.bulkapedia.presentation.components.LoginComponent

class DefaultLoginComponent(
    context: ComponentContext
) : LoginComponent, ComponentContext by context {

    private val _model = MutableStateFlow(
        LoginComponent.Model("", "")
    )

    override val model: StateFlow<LoginComponent.Model>
        get() = _model.asStateFlow()

    override fun onEmailChanged(email: String) {
        _model.value = model.value.copy(email = email)
    }

    override fun onPasswordChanged(password: String) {
        _model.value = model.value.copy(password = password)
    }

    override fun onLoginClick() {
        TODO("Login click implementation")
    }

    override fun onSignUpClick() {
        TODO("Not yet implemented")
    }

    override fun onForgetPasswordClick() {
        TODO("Not yet implemented")
    }

}
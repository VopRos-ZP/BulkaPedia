package ru.bulkapedia.presentation.screens.signIn.mvi

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.launch
import ru.bulkapedia.di.qualifiers.LoggingSF
import ru.bulkapedia.domain.repository.AuthRepository
import javax.inject.Inject

@OptIn(ExperimentalMviKotlinApi::class)
class SignInStoreImpl @Inject constructor(
    @LoggingSF
    private val storeFactory: StoreFactory,
    private val authRepository: AuthRepository,
) : SignInStore, Store<SignIn.Intent, SignIn.State, SignIn.Label>
by storeFactory.create<SignIn.Intent, Nothing, SignIn.Msg, SignIn.State, SignIn.Label>(
    name = "SignInStore",
    initialState = SignIn.State(),
    executorFactory = coroutineExecutorFactory {
        onIntent<SignIn.Intent.EmailChanged> {
            dispatch(SignIn.Msg.EmailChanged(it.value))
        }
        onIntent<SignIn.Intent.PasswordChanged> {
            dispatch(SignIn.Msg.PasswordChanged(it.value))
        }
        onIntent<SignIn.Intent.LoginClick> {
            launch {
                try {
                    when (authRepository.signIn(state.email, state.password)) {
                        true -> {/*publish(SignIn.Label.Navigate())*/}
                        else -> {}
                    }
                } catch (e: Exception) {
                    dispatch(SignIn.Msg.Error(e.localizedMessage))
                }
            }
        }
        onIntent<SignIn.Intent.CloseError> {
            dispatch(SignIn.Msg.Error())
        }
    },
    reducer = {
        when (it) {
            is SignIn.Msg.Loading -> copy(isLoading = it.isLoading)
            is SignIn.Msg.EmailChanged -> copy(email = it.value)
            is SignIn.Msg.PasswordChanged -> copy(password = it.value)
            is SignIn.Msg.Error -> copy(message = it.message)
        }
    }
)
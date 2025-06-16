package ru.bulkapedia.presentation.ui.screens.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SplashViewModel(

) : ContainerHost<Unit, SplashSideEffect>, ViewModel() {

    override val container = container<Unit, SplashSideEffect>(
        initialState = Unit,
        onCreate = {
            delay(2000)
            postSideEffect(SplashSideEffect.OnFinish)
        }
    )

}
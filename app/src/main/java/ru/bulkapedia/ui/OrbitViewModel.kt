package ru.bulkapedia.ui

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class OrbitViewModel<State : Any, SideEffect : Any>(
    initialState: State
) : ContainerHost<State, SideEffect>, ViewModel() {

    override val container = container<State, SideEffect>(initialState = initialState)

}
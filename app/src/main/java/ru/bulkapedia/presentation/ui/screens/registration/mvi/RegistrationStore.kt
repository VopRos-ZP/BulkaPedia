package ru.bulkapedia.presentation.ui.screens.registration.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface RegistrationStore : Store<Registration.Intent, Registration.State, Registration.Label>

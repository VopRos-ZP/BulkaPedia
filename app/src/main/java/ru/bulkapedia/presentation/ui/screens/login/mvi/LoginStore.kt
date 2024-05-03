package ru.bulkapedia.presentation.ui.screens.login.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface LoginStore : Store<Login.Intent, Login.State, Login.Label>

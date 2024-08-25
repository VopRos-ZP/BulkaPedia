package ru.bulkapedia.presentation.screens.login

import com.arkivanov.mvikotlin.core.store.Store

interface LoginStore : Store<Login.Intent, Login.State, Login.Label>
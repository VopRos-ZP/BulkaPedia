package ru.bulkapedia.presentation.screens.signIn.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface SignInStore : Store<SignIn.Intent, SignIn.State, SignIn.Label>

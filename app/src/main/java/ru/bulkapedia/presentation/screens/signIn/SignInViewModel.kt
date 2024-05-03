package ru.bulkapedia.presentation.screens.signIn

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.bulkapedia.presentation.screens.signIn.mvi.SignInStore
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val store: SignInStore
) : ViewModel(), SignInStore by store
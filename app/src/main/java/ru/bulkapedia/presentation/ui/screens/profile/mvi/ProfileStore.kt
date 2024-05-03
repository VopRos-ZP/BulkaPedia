package ru.bulkapedia.presentation.ui.screens.profile.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface ProfileStore : Store<Profile.Intent, Profile.State, Profile.Label>

package ru.bulkapedia.presentation.ui.screens.categories.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface CategoriesStore : Store<Categories.Intent, Categories.State, Categories.Label>
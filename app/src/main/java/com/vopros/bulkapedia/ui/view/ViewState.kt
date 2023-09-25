package com.vopros.bulkapedia.ui.view

sealed class ViewState {
    object Loading: ViewState()
    data class Success<T>(val data: T): ViewState()
    data class Error(val message: String): ViewState()
}
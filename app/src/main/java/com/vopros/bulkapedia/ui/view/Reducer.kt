package com.vopros.bulkapedia.ui.view

fun interface Reducer<I> {
    suspend fun reduce(intent: I, state: ViewState): ViewState
}
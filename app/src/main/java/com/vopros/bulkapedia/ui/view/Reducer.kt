package com.vopros.bulkapedia.ui.view

fun interface Reducer<I> {
    suspend fun execute(intent: I, state: ViewState)
}
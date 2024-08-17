package ru.bulkapedia.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn

abstract class BaseUseCase {

    private val scope = CoroutineScope(Dispatchers.IO)

    protected fun <T> Flow<T>.stateIn(initialValue: T) = distinctUntilChanged()
        .stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = initialValue
        )

    protected fun <T> Flow<List<T>>.stateIn() = stateIn(emptyList())

}
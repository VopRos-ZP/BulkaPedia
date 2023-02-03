package com.bulkapedia.compose.events

interface EventHandler<T> {
    fun obtainEvent(event: T)
}
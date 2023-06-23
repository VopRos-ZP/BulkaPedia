package com.bulkapedia.compose.data

open class Entity {
    open lateinit var id: String
    open fun toData(): MutableMap<String, Any> = mutableMapOf()
}
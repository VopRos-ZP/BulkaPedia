package com.vopros.bulkapedia.core

open class Entity(open val id: String) {
    open fun toData(): Map<String, Any> = emptyMap()
}
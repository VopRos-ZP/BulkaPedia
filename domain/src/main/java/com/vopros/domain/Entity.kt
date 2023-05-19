package com.vopros.domain

open class Entity {
    open var id: String = ""
    open fun toData(): Map<String, Any> = emptyMap()
}
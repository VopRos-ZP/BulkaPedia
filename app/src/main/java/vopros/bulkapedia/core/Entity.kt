package vopros.bulkapedia.core

open class Entity(open val id: String) {
    open fun isEmpty(): Boolean = id.isEmpty()
    open fun toData(): Map<String, Any> = emptyMap()
}
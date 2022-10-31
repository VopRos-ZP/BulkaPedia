package com.bulkapedia.gears

data class Effect (
    var number: Int,
    val percent: Boolean,
    val description: Int
) {

    override fun hashCode(): Int {
        var result = 51
        result = 31 * result + percent.hashCode()
        result = 31 * result + description
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as Effect

        return (other.description == description && other.percent == percent)
    }
}
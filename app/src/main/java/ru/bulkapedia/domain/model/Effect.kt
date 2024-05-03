package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Effect(
    val id: String,
    val description: String,
    val number: Int,
    val percent: Boolean,
    val ranks: IntArray
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Effect

        if (id != other.id) return false
        if (description != other.description) return false
        if (number != other.number) return false
        if (percent != other.percent) return false
        if (!ranks.contentEquals(other.ranks)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + number
        result = 31 * result + percent.hashCode()
        result = 31 * result + ranks.contentHashCode()
        return result
    }

}

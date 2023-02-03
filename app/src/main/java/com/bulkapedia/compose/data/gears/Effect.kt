package com.bulkapedia.compose.data.gears

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Effect(
    var number: Int,
    val percent: Boolean,
    val description: String
): Parcelable {

    override fun hashCode(): Int {
        var result = 51
        result = 31 * result + percent.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as Effect

        return (other.description == description && other.percent == percent)
    }

    override fun toString(): String {
        return "${if (number > 0) "+" else ""}$number${if (percent) "%" else ""} $description"
    }

}
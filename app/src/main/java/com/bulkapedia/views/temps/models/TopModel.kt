package com.bulkapedia.views.temps.models

import android.os.Parcel
import android.os.Parcelable
import com.bulkapedia.data.sets.UserSet

data class TopModel (
    val number: Int,
    val nickname: String,
    val set: UserSet
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        TODO("set")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
        parcel.writeString(nickname)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopModel> {
        override fun createFromParcel(parcel: Parcel): TopModel {
            return TopModel(parcel)
        }

        override fun newArray(size: Int): Array<TopModel?> {
            return arrayOfNulls(size)
        }
    }
}
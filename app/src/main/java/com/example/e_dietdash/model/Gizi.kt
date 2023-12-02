package com.example.e_dietdash.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gizi(
    var strId: String? = "0",
    var natrium: Int? = 0,
    var kalium: Int? = 0,
    var serat: Int? = 0,
    var lemak: Int? = 0,
    var date: String? = "yyyy-mm-dd"
): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }
}

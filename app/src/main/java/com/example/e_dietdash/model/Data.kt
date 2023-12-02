package com.example.e_dietdash.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    var strId: String = "0",
    var strName: String? = null,
    var strSistolik: Int? = 0,
    var intAge: Int? = 0,
    var strDiastolik: Int? = 0,
    var jk: String? = null,
    var grades: Int? = 0,
    var strBobot: Int? = 0,
    var strTinggi: Int? = 0
): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }
}

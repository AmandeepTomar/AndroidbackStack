package com.amandeep.androidbackstack.activitylifecycle

import android.os.Parcel
import android.os.Parcelable

data class ShareDataClassWithParcelable(val data: String?, val isData: Boolean =false, val isNotData:Boolean=false) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()
//       parcel.readBoolean(),
//        parcel.readBoolean()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
//        parcel.writeBoolean(isData)
//        parcel.writeBoolean(isNotData)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShareDataClassWithParcelable> {
        override fun createFromParcel(parcel: Parcel): ShareDataClassWithParcelable {
            return ShareDataClassWithParcelable(parcel)
        }

        override fun newArray(size: Int): Array<ShareDataClassWithParcelable?> {
            return arrayOfNulls(size)
        }
    }
}
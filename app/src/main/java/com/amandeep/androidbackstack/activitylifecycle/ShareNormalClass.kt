package com.amandeep.androidbackstack.activitylifecycle

import android.os.Parcel
import android.os.Parcelable

class ShareNormalClass(private val name:String,private val isData:Boolean) : Parcelable{

    val nameValue:String
    get() = name
    val isdatavalue:Boolean
    get() = isData

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeByte(if (isData) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShareNormalClass> {
        override fun createFromParcel(parcel: Parcel): ShareNormalClass {
            return ShareNormalClass(parcel)
        }

        override fun newArray(size: Int): Array<ShareNormalClass?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Name $name and isData $isData"
    }

}
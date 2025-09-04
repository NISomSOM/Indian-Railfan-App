package com.example.indianrailfanapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Locomotive(
    val locoId:String,
    val locoName:String,
    val locoType:String,
    val locoImage:String
): Parcelable

data class LocoResponse(
    val locos:List<Locomotive>
)


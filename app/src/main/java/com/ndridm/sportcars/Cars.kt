package com.ndridm.sportcars

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cars(
    val name: String,
    val description: String,
    val rating: String,
    val photo: String,
    val manufacturer: String,
    val production: String,
    val engine: String,
    val assembly: String,
    val designer: String
) : Parcelable

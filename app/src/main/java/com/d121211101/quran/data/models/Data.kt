package com.d121211101.quran.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Data(
    val englishName: String?,
    val englishNameTranslation: String?,
    val name: String?,
    val number: Int?,
    val numberOfAyahs: Int?,
    val revelationType: String?
) : Parcelable
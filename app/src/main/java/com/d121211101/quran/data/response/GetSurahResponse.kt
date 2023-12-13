package com.d121211101.quran.data.response

import com.d121211101.quran.data.models.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSurahResponse(
    @SerialName("code")
    val code: Int?,
    @SerialName("data")
    val data: List<Data>,
    @SerialName("status")
    val status: String?
)
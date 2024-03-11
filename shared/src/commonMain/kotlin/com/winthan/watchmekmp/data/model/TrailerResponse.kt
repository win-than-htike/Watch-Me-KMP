package com.winthan.watchmekmp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerResponse(
    @SerialName("id")
    val id: String?,
    @SerialName("results")
    val data: List<TrailerDto>?
)

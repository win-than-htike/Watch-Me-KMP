package com.winthan.watchmekmp.data.model


import com.winthan.watchmekmp.domain.model.Trailer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerDto(
    @SerialName("id")
    val id: String?,
    @SerialName("iso_3166_1")
    val iso31661: String?,
    @SerialName("iso_639_1")
    val iso6391: String?,
    @SerialName("key")
    val key: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("official")
    val official: Boolean?,
    @SerialName("published_at")
    val publishedAt: String?,
    @SerialName("site")
    val site: String?,
    @SerialName("size")
    val size: Int?,
    @SerialName("type")
    val type: String?
)

fun TrailerDto?.toDomain(): Trailer {
    return Trailer(
        id = this?.id.orEmpty(),
        iso31661 = this?.iso31661.orEmpty(),
        iso6391 = this?.iso6391.orEmpty(),
        key = this?.key.orEmpty(),
        name = this?.name.orEmpty(),
        official = this?.official ?: false,
        publishedAt = this?.publishedAt.orEmpty(),
        site = this?.site.orEmpty(),
        size = this?.size ?: 0,
        type = this?.type.orEmpty()
    )
}

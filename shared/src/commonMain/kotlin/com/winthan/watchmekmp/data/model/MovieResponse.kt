package com.winthan.watchmekmp.data.model

@kotlinx.serialization.Serializable
internal data class MovieResponse(
    val results: List<MovieDto>? = null,
)

internal fun MovieResponse.toDomain() = results?.map { it.toDomain() } ?: emptyList()

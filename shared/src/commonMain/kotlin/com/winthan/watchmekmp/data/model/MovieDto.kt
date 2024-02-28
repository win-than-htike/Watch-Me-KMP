package com.winthan.watchmekmp.data.model

import com.winthan.watchmekmp.domain.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDto(
    val id: Int,
    val title: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("vote_average")
    val voteAverage: Double
)

internal fun MovieDto?.toDomain() = Movie(
    id = this?.id ?: 0,
    title = this?.title.orEmpty(),
    description = this?.overview.orEmpty(),
    imageUrl = "https://image.tmdb.org/t/p/w500/${this?.posterPath}",
    releaseDate = this?.releaseDate.orEmpty()
)
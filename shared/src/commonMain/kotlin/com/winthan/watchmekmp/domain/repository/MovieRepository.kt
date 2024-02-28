package com.winthan.watchmekmp.domain.repository

import com.winthan.watchmekmp.domain.model.Movie

internal interface MovieRepository {

    suspend fun getPopular(page: Int): List<Movie>

    suspend fun getNowPlaying(page: Int): List<Movie>

    suspend fun getTopRated(page: Int): List<Movie>

    suspend fun getUpcoming(page: Int): List<Movie>

    suspend fun getById(movieId: Int): Movie

}
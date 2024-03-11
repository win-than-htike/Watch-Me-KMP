package com.winthan.watchmekmp.data.repository

import com.winthan.watchmekmp.data.model.toDomain
import com.winthan.watchmekmp.data.remote.MovieRemoteDataSource
import com.winthan.watchmekmp.domain.model.Movie
import com.winthan.watchmekmp.domain.model.Trailer
import com.winthan.watchmekmp.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getPopular(
        page: Int
    ): List<Movie> {
        return remoteDataSource.getPopular()?.results?.map {
            it.toDomain()
        } ?: emptyList()
    }

    override suspend fun getNowPlaying(page: Int): List<Movie> {
        return remoteDataSource.getNowPlaying(page)?.results?.map {
            it.toDomain()
        } ?: emptyList()
    }

    override suspend fun getTopRated(page: Int): List<Movie> {
        return remoteDataSource.getTopRated(page)?.results?.map {
            it.toDomain()
        } ?: emptyList()
    }

    override suspend fun getUpcoming(page: Int): List<Movie> {
        return remoteDataSource.getUpcoming(page)?.results?.map {
            it.toDomain()
        } ?: emptyList()
    }

    override suspend fun getById(movieId: Int): Movie {
        return remoteDataSource.getById(movieId).toDomain()
    }

    override suspend fun getTrailers(movieId: Int): List<Trailer> {
        return remoteDataSource.getTrailers(movieId)?.data?.map {
            it.toDomain()
        } ?: emptyList()
    }


}
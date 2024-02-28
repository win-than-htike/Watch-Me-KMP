package com.winthan.watchmekmp.data.remote

import com.winthan.watchmekmp.utils.Dispatcher
import kotlinx.coroutines.withContext

internal class MovieRemoteDataSource(
    private val apiService: MovieService,
    private val dispatcher: Dispatcher
) {

    suspend fun getPopular(
        page: Int = 1
    ) = safeApiCall {
        apiService.getPopularMovies(page)
    }

    suspend fun getNowPlaying(
        page: Int = 1
    ) = safeApiCall {
        apiService.getNowPlayingMovies(page)
    }

    suspend fun getTopRated(
        page: Int = 1
    ) = safeApiCall {
        apiService.getTopRatedMovies(page)
    }

    suspend fun getUpcoming(
        page: Int = 1
    ) = safeApiCall {
        apiService.getUpcomingMovies(page)
    }

    suspend fun getById(
        movieId: Int
    ) = safeApiCall {
        apiService.getMovie(movieId)
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): T? = withContext(dispatcher.io) {
        try {
            apiCall.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}